package com.example.Rent_a_Car.services;


import com.example.Rent_a_Car.model.entity.Address;
import com.example.Rent_a_Car.model.entity.Street;
import com.example.Rent_a_Car.model.entity.Town;
import com.example.Rent_a_Car.repository.AddressRepository;
import com.example.Rent_a_Car.repository.StreetRepository;
import com.example.Rent_a_Car.repository.TownRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    private final TownRepository townRepository;

    private final StreetRepository streetRepository;

    public AddressService(AddressRepository addressRepository, TownRepository townRepository, StreetRepository streetRepository) {
        this.addressRepository = addressRepository;
        this.townRepository = townRepository;
        this.streetRepository = streetRepository;
    }

    public Address getAddress(String town, String street, String number) {

        return this.addressRepository
                .findByTownNameAndStreetNameAndNumber(town, street, number)
                .orElse(new Address(
                        this.townRepository.findByName(town).orElse(new Town().setName(town)),
                        this.streetRepository.findByName(street).orElse(new Street().setName(street)),
                        number
                ));
    }


}



