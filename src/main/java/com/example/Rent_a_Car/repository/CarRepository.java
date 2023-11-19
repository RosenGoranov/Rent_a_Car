package com.example.Rent_a_Car.repository;


import com.example.Rent_a_Car.model.entity.CarEntity;
import com.example.Rent_a_Car.model.enums.FuelTypeEnums;
import com.example.Rent_a_Car.model.enums.TransmissionsEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findAllByModelNameAndTransmissionNameAndFuelTypeName(String model,TransmissionsEnum transmission,FuelTypeEnums fuelType);

    CarEntity findByModelNameAndTransmissionNameAndFuelTypeName(String model,TransmissionsEnum transmission,FuelTypeEnums fuelType);

    Page<CarEntity> getAllByModelBrandName(String brand, Pageable pageable);
}


