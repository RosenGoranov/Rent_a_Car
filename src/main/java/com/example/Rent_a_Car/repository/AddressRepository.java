package com.example.Rent_a_Car.repository;


import com.example.Rent_a_Car.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {


   Optional <AddressEntity> findByTownName(String town);

}
