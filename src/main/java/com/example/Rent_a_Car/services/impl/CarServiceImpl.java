package com.example.Rent_a_Car.services.impl;


import com.example.Rent_a_Car.model.dto.CarForRentDTO;
import com.example.Rent_a_Car.model.dto.CarRegisterDTO;
import com.example.Rent_a_Car.model.dto.RentCarUserModel;
import com.example.Rent_a_Car.model.entity.*;
import com.example.Rent_a_Car.model.enums.FuelTypeEnums;
import com.example.Rent_a_Car.model.enums.TransmissionsEnum;
import com.example.Rent_a_Car.repository.*;
import com.example.Rent_a_Car.services.CarService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    private final BrandRepository brandRepository;

    private final ModelRepository modelRepository;

    private final TransmissionRepository transmissionRepository;

    private final FuelTypeRepository fuelTypeRepository;

    public CarServiceImpl(CarRepository carRepository,
                          ModelMapper modelMapper,
                          UserRepository userRepository,
                          BrandRepository brandRepository,
                          ModelRepository modelRepository,
                          TransmissionRepository transmissionRepository,
                          FuelTypeRepository fuelTypeRepository) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.transmissionRepository = transmissionRepository;
        this.fuelTypeRepository = fuelTypeRepository;
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
    @Transactional
    public void addNewCar(CarRegisterDTO carRegisterDTO) {
        Optional<BrandEntity> optionalBrand = this.brandRepository.findByName(carRegisterDTO.getBrand());
        TransmissionEntity transmission = this.transmissionRepository.findByName(carRegisterDTO.getTransmission());
        FuelTypeEntity fuelType = this.fuelTypeRepository.findByName(carRegisterDTO.getFuelType());

        CarEntity car = modelMapper.map(carRegisterDTO, CarEntity.class)
                .setTransmission(transmission)
                .setFuelType(fuelType)
                .setReserved(false);

        if (optionalBrand.isPresent()) {
            ModelEntity model = optionalBrand.get()
                    .getModels()
                    .stream()
                    .filter(modelEntity -> modelEntity.getName()
                            .equals(carRegisterDTO.getModel()))
                    .findFirst()
                    .orElse(new ModelEntity().setName(carRegisterDTO.getModel()));
            model.setBrand(optionalBrand.get());
            car.setModel(model);

        } else {
            BrandEntity brandEntity = new BrandEntity()
                    .setName(carRegisterDTO.getBrand());
            Optional<ModelEntity> optionalModel = this.modelRepository.findByName(carRegisterDTO.getModel());
            if (optionalModel.isPresent()){
                brandEntity.addModel(optionalModel.get());
                optionalModel.get().setBrand(brandEntity);
                car.setModel(optionalModel.get());
            }else {
                ModelEntity model = new ModelEntity().setName(carRegisterDTO.getModel());
                brandEntity.addModel(model);
                model.setBrand(brandEntity);
                car.setModel(model);
            }


        }


        this.carRepository.save(car);
    }


}
