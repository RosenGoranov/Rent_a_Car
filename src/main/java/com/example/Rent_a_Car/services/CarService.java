package com.example.Rent_a_Car.services;

import com.example.Rent_a_Car.model.dto.CarDTO;
import com.example.Rent_a_Car.model.dto.CarForRentDTO;
import com.example.Rent_a_Car.model.dto.CarRegisterDTO;
import com.example.Rent_a_Car.model.dto.RentCarUserModel;
import com.example.Rent_a_Car.model.entity.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {

    List<CarForRentDTO> getAllCar();

    CarForRentDTO rent(RentCarUserModel rentCarUserModel, long id);

    void addNewCar(CarRegisterDTO carRegisterDTO);

    Page<CarDTO> allByBrand(String brand, Pageable pageable);




}
