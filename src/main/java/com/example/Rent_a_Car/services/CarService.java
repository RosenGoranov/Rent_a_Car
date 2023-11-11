package com.example.Rent_a_Car.services;

import com.example.Rent_a_Car.model.dto.CarForRentDTO;
import com.example.Rent_a_Car.model.dto.CarRegisterDTO;
import com.example.Rent_a_Car.model.dto.RentCarUserModel;

import java.util.List;

public interface CarService {

    List<CarForRentDTO> getAllCar();

    CarForRentDTO rent(RentCarUserModel rentCarUserModel, long id);

    void create(CarRegisterDTO carRegisterDTO);




}
