package com.example.Rent_a_Car.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.aop.config.AdviceEntry;


public class AddressDTO {

    @NotBlank(message = "Town is required")
    private String town;

    @NotBlank(message = "Town is required")
    private String street;

    @NotBlank(message = "Town is required")
    private String number;

    public AddressDTO() {
    }

    public AddressDTO(
            String town,
            String street,
            String number) {
        this.town = town;
        this.street = street;
        this.number = number;
    }

    public static AddressDTO builder() {
        AddressDTO addressDTO = new AddressDTO();
        return addressDTO;
    }


    public String getTown() {
        return town;
    }

    public AddressDTO setTown(String town) {
        this.town = town;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public AddressDTO setNumber(String number) {
        this.number = number;
        return this;
    }
}
