package com.example.Rent_a_Car.services;


import com.example.Rent_a_Car.model.entity.Address;
import com.example.Rent_a_Car.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddress(String town, String street, String number){
        return this.addressRepository.findByTownAndStreetAndNumber(town,street,number);
    }
}
