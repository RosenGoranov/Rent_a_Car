package com.example.Rent_a_Car.repository;

import com.example.Rent_a_Car.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<ModelEntity,Long> {
}
