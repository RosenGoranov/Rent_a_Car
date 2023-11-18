package com.example.Rent_a_Car.repository;


import com.example.Rent_a_Car.model.entity.RoleEntity;
import com.example.Rent_a_Car.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    RoleEntity findByName(RoleEnum name);
}
