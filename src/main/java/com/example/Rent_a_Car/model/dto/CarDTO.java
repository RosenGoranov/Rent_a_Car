package com.example.Rent_a_Car.model.dto;

import com.example.Rent_a_Car.model.enums.FuelTypeEnums;
import com.example.Rent_a_Car.model.enums.TransmissionsEnum;

import java.math.BigDecimal;

public class CarDTO {
    private String model;

    private TransmissionsEnum transmissions;

    private FuelTypeEnums fuelType;

    private BigDecimal rentPerDay;

    private String imgURL;

    public String getModel() {
        return model;
    }

    public CarDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public TransmissionsEnum getTransmissions() {
        return transmissions;
    }

    public CarDTO setTransmissions(TransmissionsEnum transmissions) {
        this.transmissions = transmissions;
        return this;
    }

    public FuelTypeEnums getFuelType() {
        return fuelType;
    }

    public CarDTO setFuelType(FuelTypeEnums fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public BigDecimal getRentPerDay() {
        return rentPerDay;
    }

    public CarDTO setRentPerDay (BigDecimal pricePerDay) {
        this.rentPerDay = pricePerDay;
        return this;
    }

    public String getImgURL() {
        return imgURL;
    }

    public CarDTO setImgURL(String imgURL) {
        this.imgURL = imgURL;
        return this;
    }
}
