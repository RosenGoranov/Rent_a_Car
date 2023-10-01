package com.example.Rent_a_Car.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ExistUserException extends RuntimeException {

    public ExistUserException(String msg) {
        super();
    }

}
