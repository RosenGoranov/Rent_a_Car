package com.example.Rent_a_Car.repository;


import com.example.Rent_a_Car.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository  extends JpaRepository<Address,Long> {
    Optional<Address> findByTownNameAndStreetNameAndNumber(String town, String street, String number);

    Optional<Address> findByTownName(String town);

    Optional<Address> findByStreetName(String street);

}
