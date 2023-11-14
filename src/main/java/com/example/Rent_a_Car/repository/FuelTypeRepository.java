package com.example.Rent_a_Car.repository;

import com.example.Rent_a_Car.model.entity.FuelTypeEntity;
import com.example.Rent_a_Car.model.enums.FuelTypeEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelTypeEntity,Long> {
    FuelTypeEntity findByName(FuelTypeEnums fuelType);
}
