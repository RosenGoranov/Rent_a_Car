package com.example.Rent_a_Car.services.impl;


import com.example.Rent_a_Car.model.dto.CarForRentDTO;
import com.example.Rent_a_Car.model.dto.RentCarUserModel;
import com.example.Rent_a_Car.model.entity.CarEntity;
import com.example.Rent_a_Car.model.entity.UserEntity;
import com.example.Rent_a_Car.model.enums.FuelTypeEnums;
import com.example.Rent_a_Car.model.enums.TransmissionsEnum;
import com.example.Rent_a_Car.repository.*;
import com.example.Rent_a_Car.services.CarService;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private UserRepository userRepository;


    public CarServiceImpl(CarRepository carRepository, UserRepository userRepository, ModelRepository modelRepository, TransmissionRepository transmissionRepository, FuelTypeRepository fuelTypeRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }


    @Override
    public List<CarForRentDTO> getAllCar() {
        return this.carRepository
                .findAll()
                .stream()
                .filter(carEntity -> !carEntity.isReserved())
                .map(carEntity -> modelMapper.map(carEntity, CarForRentDTO.class))
                .toList();

    }

    @Override
    public CarForRentDTO rent(RentCarUserModel rentCarUserModel, long id) {
        String model = rentCarUserModel.getModelDTO().getName();
        TransmissionsEnum transmissions = rentCarUserModel.getTransmissions();
        FuelTypeEnums fuelType = rentCarUserModel.getFuelType();
        UserEntity user = this.userRepository.findById(id).orElse(null);

        CarEntity carEntity = this.carRepository
                .findByModelNameAndTransmissionNameAndFuelTypeName(model, transmissions, fuelType);
        carEntity.setReserved(true);
        carEntity.setUserEntity(user);
        carRepository.save(carEntity);


        return modelMapper.map(carEntity, CarForRentDTO.class);
    }

    @Override
    public void create(CarForRentDTO carForRentDTO) {
//todo
    }


}
