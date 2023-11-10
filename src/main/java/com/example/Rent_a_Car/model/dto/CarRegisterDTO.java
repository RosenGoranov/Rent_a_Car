package com.example.Rent_a_Car.model.dto;

import com.example.Rent_a_Car.model.entity.FuelTypeEntity;
import com.example.Rent_a_Car.model.entity.ModelEntity;
import com.example.Rent_a_Car.model.entity.TransmissionEntity;
import jakarta.validation.constraints.NotBlank;


import java.math.BigDecimal;
import java.time.LocalDate;


public class CarRegisterDTO {

    @NotBlank
    private ModelEntity modelEntity;

    @NotBlank
    private TransmissionEntity transmissionEntity;

    @NotBlank
    private FuelTypeEntity fuelTypeEntity;

    @NotBlank
    private String vinNumber;

    @NotBlank
    private String plate;

    @NotBlank
    private LocalDate regDate;

    @NotBlank
    private BigDecimal rentPerDay;

    @NotBlank
    private String description;

    public CarRegisterDTO() {
    }

    public CarRegisterDTO(ModelEntity modelEntity,
                          TransmissionEntity transmissionEntity,
                          FuelTypeEntity fuelTypeEntity,
                          String vinNumber,
                          String plate,
                          LocalDate regDate,
                          BigDecimal rentPerDay,
                          String description) {
        this.modelEntity = modelEntity;
        this.transmissionEntity = transmissionEntity;
        this.fuelTypeEntity = fuelTypeEntity;
        this.vinNumber = vinNumber;
        this.plate = plate;
        this.regDate = regDate;
        this.rentPerDay = rentPerDay;
        this.description = description;
    }

    public ModelEntity getModel() {
        return modelEntity;
    }

    public CarRegisterDTO setModel(ModelEntity modelEntity) {
        this.modelEntity = modelEntity;
        return this;
    }

    public TransmissionEntity getTransmission() {
        return transmissionEntity;
    }

    public CarRegisterDTO setTransmission(TransmissionEntity transmissionEntity) {
        this.transmissionEntity = transmissionEntity;
        return this;
    }

    public FuelTypeEntity getFuelType() {
        return fuelTypeEntity;
    }

    public CarRegisterDTO setFuelType(FuelTypeEntity fuelTypeEntity) {
        this.fuelTypeEntity = fuelTypeEntity;
        return this;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public CarRegisterDTO setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
        return this;
    }

    public String getPlate() {
        return plate;
    }

    public CarRegisterDTO setPlate(String plate) {
        this.plate = plate;
        return this;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public CarRegisterDTO setRegDate(LocalDate regDate) {
        this.regDate = regDate;
        return this;
    }

    public BigDecimal getRentPerDay() {
        return rentPerDay;
    }

    public CarRegisterDTO setRentPerDay(BigDecimal rentPerDay) {
        this.rentPerDay = rentPerDay;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CarRegisterDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
