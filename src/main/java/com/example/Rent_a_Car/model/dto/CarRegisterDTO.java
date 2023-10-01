package com.example.Rent_a_Car.model.dto;

import com.example.Rent_a_Car.model.entity.FuelType;
import com.example.Rent_a_Car.model.entity.Model;
import com.example.Rent_a_Car.model.entity.Transmission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.time.LocalDate;


public class CarRegisterDTO {

    @NotBlank
    private Model model;

    @NotBlank
    private Transmission transmission;

    @NotBlank
    private FuelType fuelType;

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

    public CarRegisterDTO(Model model,
                          Transmission transmission,
                          FuelType fuelType,
                          String vinNumber,
                          String plate,
                          LocalDate regDate,
                          BigDecimal rentPerDay,
                          String description) {
        this.model = model;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.vinNumber = vinNumber;
        this.plate = plate;
        this.regDate = regDate;
        this.rentPerDay = rentPerDay;
        this.description = description;
    }

    public Model getModel() {
        return model;
    }

    public CarRegisterDTO setModel(Model model) {
        this.model = model;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public CarRegisterDTO setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public CarRegisterDTO setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
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
