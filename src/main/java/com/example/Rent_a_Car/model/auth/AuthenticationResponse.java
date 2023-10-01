package com.example.Rent_a_Car.model.auth;


import org.antlr.v4.runtime.Token;
import org.modelmapper.spi.Tokens;

public class AuthenticationResponse {

    private String token;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public static AuthenticationResponse builder() {
        return new AuthenticationResponse();
    }


    public String getToken() {
        return token;
    }

    public AuthenticationResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
