package com.example.Rent_a_Car.services;


import com.example.Rent_a_Car.model.entity.Role;
import com.example.Rent_a_Car.model.enums.RoleEnum;
import com.example.Rent_a_Car.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service

public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRole(RoleEnum name) {
        return this.roleRepository.findByName(name);
    }

    public void save(Role role) {
        this.roleRepository.saveAndFlush(role);
    }
}
