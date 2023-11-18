package com.example.Rent_a_Car.valodations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class NotBlankPriceValidator implements ConstraintValidator<NotBlankPrice, BigDecimal> {


    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {

        return value != null;
    }
}
