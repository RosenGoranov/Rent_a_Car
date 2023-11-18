package com.example.Rent_a_Car.repository;

import com.example.Rent_a_Car.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<ModelEntity,Long> {
    Optional<ModelEntity> findByName(String model);
}
