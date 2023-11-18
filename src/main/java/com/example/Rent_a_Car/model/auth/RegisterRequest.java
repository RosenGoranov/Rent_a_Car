package com.example.Rent_a_Car.model.auth;

import com.example.Rent_a_Car.model.dto.AddressDTO;
import jakarta.validation.constraints.*;



public class RegisterRequest {


    @NotBlank(message = "Firs name required")
    @Size(min = 3, max = 20, message = "First Name must be between 3 and 20 characters and cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name required")
    @Size(min = 3, max = 20, message = "Last Name must be between 3 and 20 characters and cannot be empty")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 8, max = 30, message = "Password must between 8 and 30 characters and cannot be empty")
    private String password;

    @NotBlank(message = "Confirm Password required")
    @Size(min = 8, max = 30, message = "Password must between 8 and 30 characters and cannot be empty")
    private String confirmPassword;


    private AddressDTO addressDTO;


    public RegisterRequest() {
    }

    public RegisterRequest(String firstName, String lastName, String email, String password, String confirmPassword, AddressDTO addressDTO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.addressDTO = addressDTO;
    }

    public String getFirstName() {
        return firstName;
    }

    public RegisterRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisterRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterRequest setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public RegisterRequest setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
        return this;
    }
}

