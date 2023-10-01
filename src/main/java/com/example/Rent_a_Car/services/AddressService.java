package com.example.Rent_a_Car.services;


import com.example.Rent_a_Car.model.entity.Address;
import com.example.Rent_a_Car.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> getAddress(String town, String street, String number) {
        return this.addressRepository.findByTownNameAndStreetNameAndNumber(town, street, number);
    }

    public String getAddressTown(String town) {
        Optional<Address> byTown = this.addressRepository.findByTownName(town);
        if (byTown.isEmpty()){
            return town;
        }
        return byTown.get().getTown().getName();
    }

    public String getAddressStreet(String street) {
        Optional<Address> byTown = this.addressRepository.findByStreetName(street);
        if (byTown.isEmpty()){
            return street;
        }
        return byTown.get().getStreet().getName();
    }


}
