package com.example.Rent_a_Car.services;


import com.example.Rent_a_Car.model.auth.RegisterRequest;
import com.example.Rent_a_Car.model.dto.UserDTO;
import com.example.Rent_a_Car.model.entity.User;
import com.example.Rent_a_Car.model.entity.UserEntity;
import com.example.Rent_a_Car.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Service
public interface UserService {


    UserDTO getByEmail(String email) throws RuntimeException;

    @Transactional
    boolean save(RegisterRequest request, Consumer<Authentication> successfulLoginProcessor) throws RuntimeException;

    List<UserDTO> getUsers();
}

