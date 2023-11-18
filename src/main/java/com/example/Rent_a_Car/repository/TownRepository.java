package com.example.Rent_a_Car.repository;

import com.example.Rent_a_Car.model.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town,Long> {
    Optional<Town> findByName(String town);
}
