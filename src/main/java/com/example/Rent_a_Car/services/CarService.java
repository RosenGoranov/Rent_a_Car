package com.example.Rent_a_Car.services;


import com.example.Rent_a_Car.model.dto.RentCarDTO;
import com.example.Rent_a_Car.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.modelMapper = new ModelMapper();
    }


    public List<RentCarDTO> findAll(String brand) {


        return carRepository.findAllByModelBrandName(brand)
                .stream().
                map(car -> modelMapper.map(car, RentCarDTO.class))
                .toList();
    }


}
