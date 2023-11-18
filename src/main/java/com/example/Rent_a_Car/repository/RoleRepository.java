package com.example.Rent_a_Car.repository;


import com.example.Rent_a_Car.model.entity.Role;
import com.example.Rent_a_Car.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(RoleEnum name);
}
