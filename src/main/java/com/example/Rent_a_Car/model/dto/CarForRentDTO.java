package com.example.Rent_a_Car.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;


public class CarForRentDTO {

    @Positive(message = "Please select a model")
    private long modelId;


    @NotEmpty(message = "Please select a transmission")
    private String transmission;

    @NotEmpty(message = "Please select a fuel type")
    private String fuelType;



    private long user;

    public CarForRentDTO() {
    }

    public long getModelId() {
        return modelId;
    }

    public CarForRentDTO setModelId(long modelId) {
        this.modelId = modelId;
        return this;
    }


    public String getTransmission() {
        return transmission;
    }

    public CarForRentDTO setTransmission(String transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getFuelType() {
        return fuelType;
    }

    public CarForRentDTO setFuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public long getUser() {
        return user;
    }

    public CarForRentDTO setUser(long user) {
        this.user = user;
        return this;
    }
}
