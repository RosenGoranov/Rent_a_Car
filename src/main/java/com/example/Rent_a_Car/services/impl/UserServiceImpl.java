package com.example.Rent_a_Car.services.impl;


import com.example.Rent_a_Car.model.auth.RegisterRequest;
import com.example.Rent_a_Car.model.dto.UserDTO;
import com.example.Rent_a_Car.model.entity.AddressEntity;
import com.example.Rent_a_Car.model.entity.RoleEntity;
import com.example.Rent_a_Car.model.entity.TownEntity;
import com.example.Rent_a_Car.model.entity.UserEntity;
import com.example.Rent_a_Car.model.enums.RoleEnum;
import com.example.Rent_a_Car.repository.RoleRepository;
import com.example.Rent_a_Car.repository.TownRepository;
import com.example.Rent_a_Car.repository.UserRepository;
import com.example.Rent_a_Car.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(UserRepository userRepository,
                           TownRepository townRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public UserDTO getByEmail(String email) throws RuntimeException {
        Optional<UserEntity> optional = this.userRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User with email:" + email + " not register");
        }
        return modelMapper.map(optional.get(), UserDTO.class);
    }

    @Override
    @Transactional
    public boolean save(RegisterRequest request, Consumer<Authentication> successfulLoginProcessor) throws RuntimeException {
        UserDTO userDTO = modelMapper.map(request, UserDTO.class);
        userDTO.setPassword(passwordEncoder.encode(request.getPassword()));

        Optional<UserEntity> optionalUser = this.userRepository.findByEmail(userDTO.getEmail());
        if (optionalUser.isPresent()) {
            return false;
        }
        Optional<TownEntity> optionalTown = this.townRepository.findByName(
                userDTO.getAddressDTO().getTown());
        if (optionalTown.isEmpty()) {
            optionalTown.get().setName(request.getAddressDTO().getTown());
        }

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        if (this.roleRepository.count() == 0) {
            List<RoleEntity> roleEntities = new ArrayList<>(List.of(
                    new RoleEntity(RoleEnum.ADMIN),
                    new RoleEntity(RoleEnum.MODERATOR),
                    new RoleEntity(RoleEnum.EMPLOYEE),
                    new RoleEntity(RoleEnum.USER)
            ));
            userEntity.setRole(roleEntities);
        } else {
            userEntity.setRole(new ArrayList<>(List.of(this.roleRepository.findByName(RoleEnum.USER))));
        }
        userEntity.setAddress(new AddressEntity().setTown(request.getAddressDTO().getTown()));
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

    @Override
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

