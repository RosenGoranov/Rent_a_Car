package com.example.Rent_a_Car.model.dto;

import com.example.Rent_a_Car.model.enums.FuelTypeEnums;
import com.example.Rent_a_Car.model.enums.TransmissionsEnum;
import com.example.Rent_a_Car.valodations.NotBlankPrice;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.math.BigDecimal;
import java.time.LocalDate;


public class CarRegisterDTO {

    @NotBlank(message = " Brand cannot be blank")
    private String brand;

    @NotBlank(message = "Model cannot be blank")
    private String model;

    @NotNull
    private TransmissionsEnum transmission;

    @NotNull
    private FuelTypeEnums fuelType;

    @NotBlank(message = "Vin Number cannot be blank")
    private String vinNumber;

    @NotBlank(message = "The Registration Plate cannot be blank")
    @Pattern(regexp = "[A-Z]{2}[0-9]{4}[A-Z]{2}", message = "The Registration plate is not valid")
    private String plate;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;

    @Positive
    @NotBlankPrice
    private BigDecimal rentPerDay;

    private String imgURL;


    private String description;

    public String getBrand() {
        return brand;
    }

    public CarRegisterDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarRegisterDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public TransmissionsEnum getTransmission() {
        return transmission;
    }

    public CarRegisterDTO setTransmission(TransmissionsEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public FuelTypeEnums getFuelType() {
        return fuelType;
    }

    public CarRegisterDTO setFuelType(FuelTypeEnums fuelType) {
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
