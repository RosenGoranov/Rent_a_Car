package com.example.Rent_a_Car.model.dto;

import com.example.Rent_a_Car.model.enums.FuelTypeEnums;
import com.example.Rent_a_Car.model.enums.TransmissionsEnum;

public class RentCarUserModel {

    private ModelDTO modelDTO;

    private TransmissionsEnum transmissions;

    private FuelTypeEnums fuelType;

    public ModelDTO getModelDTO() {
        return modelDTO;
    }

    public RentCarUserModel setModelDTO(ModelDTO modelDTO) {
        this.modelDTO = modelDTO;
        return this;
    }

    public TransmissionsEnum getTransmissions() {
        return transmissions;
    }

    public RentCarUserModel setTransmissions(TransmissionsEnum transmissions) {
        this.transmissions = transmissions;
        return this;
    }

    public FuelTypeEnums getFuelType() {
        return fuelType;
    }

    public RentCarUserModel setFuelType(FuelTypeEnums fuelType) {
        this.fuelType = fuelType;
        return this;
    }
}
