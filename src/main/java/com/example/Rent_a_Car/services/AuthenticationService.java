package com.example.Rent_a_Car.services;


import com.example.Rent_a_Car.model.auth.AuthenticationRequest;
import com.example.Rent_a_Car.model.auth.AuthenticationResponse;
import com.example.Rent_a_Car.model.auth.RegisterRequest;
import com.example.Rent_a_Car.model.dto.AddressDTO;
import com.example.Rent_a_Car.model.dto.UserDTO;
import com.example.Rent_a_Car.model.entity.Role;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.AssertTrue;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final AddressService addressServices;


    public AuthenticationService(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager manager, AddressService addressServices) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.manager = manager;
        this.addressServices = addressServices;
    }

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {


        Role role = null;
        if (this.userService.getUsers().size() == 0) {
            role = this.roleService.getRole(ADMIN);
        }
        role = roleService.getRole(USER);


        UserDTO userDTO = buildUserDTO(request, role);
        userDTO.setRole(role);
        boolean emailExist = userService.save(userDTO);
        if (!emailExist) {
            return AuthenticationResponse.builder().
                    setToken(null);
        }
        String jwtToken = jwtService.generateToken(userDTO);
        return AuthenticationResponse.builder().
                setToken(jwtToken);
    }

    @AssertTrue(message = "User  with this email exists")
    private boolean userExist(String email) {
        return email == null;
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request){
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDTO userDTO = userService.getByEmail(request.getEmail());


        var jwtToken = jwtService.generateToken(userDTO);
        return AuthenticationResponse.builder().
                setToken(jwtToken);
    }


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

