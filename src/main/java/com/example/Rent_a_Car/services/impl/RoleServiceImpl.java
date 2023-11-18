package com.example.Rent_a_Car.services.impl;


import com.example.Rent_a_Car.model.entity.RoleEntity;
import com.example.Rent_a_Car.model.enums.RoleEnum;
import com.example.Rent_a_Car.repository.RoleRepository;
import com.example.Rent_a_Car.services.RoleService;
import org.springframework.stereotype.Service;

@Service

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public RoleEntity getRole(RoleEnum name) {
        return this.roleRepository.findByName(name);
    }

    @Override
    public void save(RoleEntity roleEntity) {
        this.roleRepository.saveAndFlush(roleEntity);
    }
}
