package com.example.Rent_a_Car.services.impl;

import com.example.Rent_a_Car.model.dto.AddressDTO;
import com.example.Rent_a_Car.model.entity.AddressEntity;
import com.example.Rent_a_Car.repository.AddressRepository;
import com.example.Rent_a_Car.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final ModelMapper modelMapper;


    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public AddressDTO getAddress(String town) {
        Optional<AddressEntity> optionalAddress = this.addressRepository.findByTownName(town);
        return optionalAddress.map(addressEntity -> modelMapper.map(addressEntity, AddressDTO.class)).orElse(null);

    }
}
