package com.example.Rent_a_Car.services;


import com.example.Rent_a_Car.model.auth.RegisterRequest;
import com.example.Rent_a_Car.model.dto.UserDTO;
import com.example.Rent_a_Car.model.entity.Address;
import com.example.Rent_a_Car.model.entity.Role;
import com.example.Rent_a_Car.model.entity.UserEntity;
import com.example.Rent_a_Car.model.enums.RoleEnum;
import com.example.Rent_a_Car.repository.RoleRepository;
import com.example.Rent_a_Car.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Service

public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;
    private final ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserService(UserRepository userRepository, AddressService addressService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;

        this.userDetailsService = userDetailsService;
    }

    public UserDTO getByEmail(String email) throws RuntimeException {
        Optional<UserEntity> optional = this.userRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User with email:" + email + " not register");
        }
        return modelMapper.map(optional.get(), UserDTO.class);
    }

    @Transactional
    public boolean save(RegisterRequest request, Consumer<Authentication> successfulLoginProcessor) throws RuntimeException {
        UserDTO userDTO = modelMapper.map(request, UserDTO.class);
        userDTO.setPassword(passwordEncoder.encode(request.getPassword()));

        Optional<UserEntity> optionalUser = this.userRepository.findByEmail(userDTO.getEmail());
        if (optionalUser.isPresent()) {
            return false;
        }
        Address address = this.addressService.getAddress(
                userDTO.getAddressDTO().getTown(),
                userDTO.getAddressDTO().getStreet(),
                userDTO.getAddressDTO().getNumber());

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        if (this.roleRepository.count() == 0) {
            List<Role> roles = new ArrayList<>(List.of(
                    new Role(RoleEnum.ADMIN),
                    new Role(RoleEnum.MODERATOR),
                    new Role(RoleEnum.EMPLOYEE),
                    new Role(RoleEnum.USER)
            ));
            userEntity.setRole(roles);
        }else {
            userEntity.setRole(new ArrayList<>(List.of(this.roleRepository.findByName(RoleEnum.USER))));
        }
        userEntity.setAddress(address);
        this.userRepository.save(userEntity);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        successfulLoginProcessor.accept(authentication);
        return true;
    }

    public List<UserDTO> getUsers() {
        ModelMapper modelMapper = new ModelMapper();
        return this.userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

// TODO
}

