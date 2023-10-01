package com.example.Rent_a_Car.services;

import com.example.Rent_a_Car.model.dto.AddressDTO;

public interface AddressService {
    AddressDTO getAddress(String town);
}
