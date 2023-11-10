package com.example.Rent_a_Car.services;

import com.example.Rent_a_Car.model.dto.AddressDTO;
import com.example.Rent_a_Car.model.entity.AddressEntity;

public interface AddressService {



    AddressDTO getAddress(String town);
}
