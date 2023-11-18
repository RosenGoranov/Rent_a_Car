package com.example.Rent_a_Car.utils;


import com.example.Rent_a_Car.model.entity.*;
import com.example.Rent_a_Car.model.enums.FuelTypeEnums;
import com.example.Rent_a_Car.model.enums.TransmissionsEnum;
import com.example.Rent_a_Car.repository.FuelTypeRepository;
import com.example.Rent_a_Car.repository.RoleRepository;
import com.example.Rent_a_Car.repository.TransmissionRepository;
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
    private final TransmissionRepository transmissionRepository;
    private final FuelTypeRepository fuelTypeRepository;

    public InitDB(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, TransmissionRepository transmissionRepository, FuelTypeRepository fuelTypeRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.transmissionRepository = transmissionRepository;
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initRole();
        initUsers();
        initTransmission();
        initFuelType();
    }

    private void initFuelType() {
        if (this.fuelTypeRepository.count() == 0) {
            this.fuelTypeRepository.saveAll(List.of(
                    new FuelTypeEntity().setName(FuelTypeEnums.Diesel),
                    new FuelTypeEntity().setName(FuelTypeEnums.Gasoline),
                    new FuelTypeEntity().setName(FuelTypeEnums.Electric),
                    new FuelTypeEntity().setName(FuelTypeEnums.Hybrid),
                    new FuelTypeEntity().setName(FuelTypeEnums.Plug_in_hybrid)
            ));
        }
    }

    private void initTransmission() {
        if (this.transmissionRepository.count() == 0) {
            this.transmissionRepository.saveAll(List.of(
                    new TransmissionEntity().setName(TransmissionsEnum.Automatic),
                    new TransmissionEntity().setName(TransmissionsEnum.Manual),
                    new TransmissionEntity().setName(TransmissionsEnum.Semi_automatic)
            ));
        }
    }


    private void initRole() {
        if (this.roleRepository.count() == 0) {

            this.roleRepository.saveAll(List.of(
                    new RoleEntity(ADMIN),
                    new RoleEntity(MODERATOR),
                    new RoleEntity(EMPLOYEE),
                    new RoleEntity(USER)
            ));

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

