package com.example.Rent_a_Car.services;


import com.example.Rent_a_Car.model.dto.UserDTO;
import com.example.Rent_a_Car.model.entity.User;
import com.example.Rent_a_Car.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service

public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;


    @Autowired
    public UserService(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;

    }

    public UserDTO getByEmail(String email) throws RuntimeException {
        Optional<User> optional = this.userRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User name not exist");
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(optional.get(), UserDTO.class);
    }


    public void save(UserDTO userDTO) throws RuntimeException {
        Optional<User> optionalUser = this.userRepository.findByEmail(userDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new ExistUserException("Exist optionalUser email");
        }
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, User.class);

        //TODO
        this.userRepository.save(user);
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

