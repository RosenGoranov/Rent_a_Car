package com.example.Rent_a_Car.repository;


import com.example.Rent_a_Car.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends JpaRepository<Address,Long> {
    Address findByTownAndStreetAndNumber(String town, String street, String number);
}
