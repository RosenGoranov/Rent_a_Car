package com.example.Rent_a_Car.model.dto;

import com.example.Rent_a_Car.model.entity.FuelType;
import com.example.Rent_a_Car.model.entity.Model;
import com.example.Rent_a_Car.model.entity.Transmission;
import com.example.Rent_a_Car.model.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;


import java.math.BigDecimal;


public class RentCarDTO {

    @NotBlank(message = "Please select a model")
    private Model model;

    @NotBlank(message = "Please select a transmission")
    private Transmission transmission;

    @NotBlank(message = "Please select a fuel type")
    private FuelType fuelType;


    private BigDecimal rentPerDay;


    private String description;

    @NotBlank
    private UserEntity userEntity;

    public RentCarDTO() {
    }

    public RentCarDTO(Model model,
                      Transmission transmission,
                      FuelType fuelType,
                      BigDecimal rentPerDay,
                      String description,
                      UserEntity userEntity) {
        this.model = model;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.rentPerDay = rentPerDay;
        this.description = description;
        this.userEntity = userEntity;
    }

    public Model getModel() {
        return model;
    }

    public RentCarDTO setModel(Model model) {
        this.model = model;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public RentCarDTO setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public RentCarDTO setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public BigDecimal getRentPerDay() {
        return rentPerDay;
    }

    public RentCarDTO setRentPerDay(BigDecimal rentPerDay) {
        this.rentPerDay = rentPerDay;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RentCarDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public RentCarDTO setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
