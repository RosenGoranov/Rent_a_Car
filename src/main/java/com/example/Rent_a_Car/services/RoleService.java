package com.example.Rent_a_Car.services;

import com.example.Rent_a_Car.model.entity.RoleEntity;
import com.example.Rent_a_Car.model.enums.RoleEnum;

public interface RoleService {
    RoleEntity getRole(RoleEnum name);

    void save(RoleEntity roleEntity);
}
