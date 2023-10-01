package com.example.Rent_a_Car.utils;


import com.example.Rent_a_Car.model.entity.Address;
import com.example.Rent_a_Car.model.entity.Role;
import com.example.Rent_a_Car.model.entity.User;
import com.example.Rent_a_Car.repository.RoleRepository;
import com.example.Rent_a_Car.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.Rent_a_Car.model.enums.RoleEnum.ADMIN;
import static com.example.Rent_a_Car.model.enums.RoleEnum.USER;


@Component

public class InitDB implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitDB(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        if (roleRepository.count() == 0) {
//
//            roleRepository.saveAll(List.of(
//                   new Role.(ADMIN).build(),
//                    Role.builder().name(USER).build())
//            );
//        }
//
//        if (userRepository.count() == 0) {
//            Role admin = roleRepository.findByName(ADMIN);
//            Address address = Address.builder()
//                    .setTown("Sofia")
//                    .setStreet("Baba Iliica")
//                    .setNumber("bl.3");
//
//
//            User user = new User.
//                    s("Admin")
//                    .lastName("Admin")
//                    .email("admin@admin.com")
//                    .password(passwordEncoder
//                            .encode("admin.automatically_generated.password"))
//                    .address(address)
//                    .role(admin)
//                    .build();
//
//            userRepository.save(user);
//        }
    }
}

