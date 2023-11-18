package com.example.Rent_a_Car.repository;

import com.example.Rent_a_Car.model.entity.AddressEntity;
import com.example.Rent_a_Car.model.entity.TownEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<TownEntity,Long> {
    Optional<TownEntity> findByName(String town);

    @EntityGraph(value = "TownWithAddress",attributePaths = "address")
    List<TownEntity> findAllBy();
}
