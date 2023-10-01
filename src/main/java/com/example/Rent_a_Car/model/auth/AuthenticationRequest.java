package com.example.Rent_a_Car.model.auth;




public class AuthenticationRequest {

    private String email;

    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public AuthenticationRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthenticationRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
