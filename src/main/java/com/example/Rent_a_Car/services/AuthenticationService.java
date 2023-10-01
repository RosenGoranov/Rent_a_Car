package com.example.Rent_a_Car.services;


import com.example.Rent_a_Car.model.auth.AuthenticationRequest;
import com.example.Rent_a_Car.model.auth.AuthenticationResponse;
import com.example.Rent_a_Car.model.auth.RegisterRequest;
import com.example.Rent_a_Car.model.dto.AddressDTO;
import com.example.Rent_a_Car.model.dto.UserDTO;
import com.example.Rent_a_Car.model.entity.Role;
import jakarta.validation.constraints.AssertTrue;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.Rent_a_Car.model.enums.RoleEnum.ADMIN;
import static com.example.Rent_a_Car.model.enums.RoleEnum.USER;

@Service

public class AuthenticationService {

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager manager;

    public AuthenticationService(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager manager) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.manager = manager;
    }


    public AuthenticationResponse register(RegisterRequest request) {


        Role role = null;
        if (this.userService.getUsers().size() == 0) {
            role = this.roleService.getRole(ADMIN);
        } else {
            role = roleService.getRole(USER);
        }


        //TODO delete if below method works
        //var userDTO = buildUserDTO(request, role);
        UserDTO userDTO = buildUserDTO(request,role);
        userDTO.setRole(role);
        userService.save(userDTO);
        var jwtToken = jwtService.generateToken(userDTO);
        return AuthenticationResponse.builder().
                setToken(jwtToken);
    }

    @AssertTrue(message = "User  with this email exists")
    private boolean userExist(String email) {
        return email == null;
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UsernameNotFoundException {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var userDTO = userService.getByEmail(request.getEmail());


        var jwtToken = jwtService.generateToken(userDTO);
        return AuthenticationResponse.builder().
                setToken(jwtToken);
    }

    //TODO delete if below method works
    private UserDTO buildUserDTO(RegisterRequest request, Role user) {
        return UserDTO.builder()
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setEmail(request.getEmail())
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setAddressDTO(AddressDTO
                        .builder()
                        .setTown(request.getAddressDTO().getTown())
                        .setStreet(request.getAddressDTO().getStreet())
                        .setNumber(request.getAddressDTO().getNumber())
                        )
                .setRole(user);
    }

}

