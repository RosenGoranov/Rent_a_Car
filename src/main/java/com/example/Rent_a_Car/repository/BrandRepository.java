package com.example.Rent_a_Car.repository;

import com.example.Rent_a_Car.model.entity.BrandEntity;
import jakarta.persistence.NamedEntityGraph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    @EntityGraph(
            value = "brandWithModels",
            attributePaths = "models"
    )

    @Query("SELECT b FROM BrandEntity b")
    List<BrandEntity> getAll();
}
