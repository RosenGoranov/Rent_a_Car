package com.example.Rent_a_Car.repository;

import com.example.Rent_a_Car.model.entity.FuelTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelTypeEntity,Long> {
}
