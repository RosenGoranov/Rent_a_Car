package com.example.Rent_a_Car.utils;


import com.example.Rent_a_Car.model.entity.*;
import com.example.Rent_a_Car.repository.BrandRepository;
import com.example.Rent_a_Car.repository.RoleRepository;
import com.example.Rent_a_Car.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.example.Rent_a_Car.model.enums.RoleEnum.*;


@Component

public class InitDB implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final BrandRepository brandRepository;

    public InitDB(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, BrandRepository brandRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.brandRepository = brandRepository;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initRole();
        initUsers();
    }


    private void initRole() {
        if (this.roleRepository.count() == 0) {
            RoleEntity admin = new RoleEntity(ADMIN);
            RoleEntity moderator = new RoleEntity(MODERATOR);
            RoleEntity employee = new RoleEntity(EMPLOYEE);
            RoleEntity user = new RoleEntity(USER);

            this.roleRepository.save(admin);
            this.roleRepository.save(moderator);
            this.roleRepository.save(employee);
            this.roleRepository.save(user);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            UserEntity admin = (UserEntity) new UserEntity()
                    .setAccountNonLocked(true)
                    .setAccountNonExpired(true)
                    .setFirstName("Admin")
                    .setLastName("Admin")
                    .setEmail("admin@example.com")
                    .setPassword(passwordEncoder.encode("12345678"))
                    .setRole(this.roleRepository.findAll())
                    .setAddress(new AddressEntity()
                            .setTown(new TownEntity().setName("Sofia"))
                            .setStreet("bul.Vitosha")
                            .setNumber("1"));

            this.userRepository.save(admin);
        }
    }




}

