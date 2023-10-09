package com.example.Rent_a_Car.utils;


import com.example.Rent_a_Car.model.entity.*;
import com.example.Rent_a_Car.repository.RoleRepository;
import com.example.Rent_a_Car.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.Rent_a_Car.model.enums.RoleEnum.*;


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
        initRole();
        initUsers();


    }


    private void initRole() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role(ADMIN);
            Role moderator = new Role(MODERATOR);
            Role employee = new Role(EMPLOYEE);
            Role user = new Role(USER);

            this.roleRepository.save(admin);
            this.roleRepository.save(moderator);
            this.roleRepository.save(employee);
            this.roleRepository.save(user);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            BasePerson admin = new UserEntity()
                    .setFirstName("Admin")
                    .setLastName("Admin")
                    .setEmail("admin@admin.com")
                    .setRole(this.roleRepository.findAll())
                    .setPassword(passwordEncoder.encode("testtest"))
                    .setAddress(new Address()
                            .setTown(new Town().setName("Sofia"))
                            .setStreet(new Street().setName("Sofia"))
                            .setNumber("Sofia")
                    );

            this.userRepository.save((UserEntity) admin);

            BasePerson moderator = new UserEntity()
                    .setFirstName("Moderator")
                    .setLastName("Moderator")
                    .setEmail("moderator@moderator.com")
                    .setRole(List.of(this.roleRepository.findByName(MODERATOR)))
                    .setPassword(passwordEncoder.encode("testtest"))
                    .setAddress(new Address()
                            .setTown(new Town().setName("Sofia"))
                            .setStreet(new Street().setName("Sofia"))
                            .setNumber("Sofia")
                    );
            this.userRepository.save((UserEntity) moderator);

            BasePerson employee = new UserEntity()
                    .setFirstName("Employee")
                    .setLastName("Employee")
                    .setEmail("employee@employee.com")
                    .setRole(List.of(this.roleRepository.findByName(EMPLOYEE)))
                    .setPassword(passwordEncoder.encode("testtest"))
                    .setAddress(new Address()
                            .setTown(new Town().setName("Sofia"))
                            .setStreet(new Street().setName("Sofia"))
                            .setNumber("Sofia")
                    );
            this.userRepository.save((UserEntity) employee);

            BasePerson normalUser = new UserEntity()
                    .setFirstName("NormalUser")
                    .setLastName("NormalUser")
                    .setEmail("user@user.com")
                    .setRole(List.of(this.roleRepository.findByName(USER)))
                    .setPassword(passwordEncoder.encode("testtest"))
                    .setAddress(new Address()
                            .setTown(new Town().setName("Sofia"))
                            .setStreet(new Street().setName("Sofia"))
                            .setNumber("Sofia")
                    );

            this.userRepository.save((UserEntity) normalUser);
        }
    }

}

