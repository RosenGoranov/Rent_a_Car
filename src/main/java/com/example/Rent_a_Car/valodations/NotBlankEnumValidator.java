package com.example.Rent_a_Car.valodations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotBlankEnumValidator implements ConstraintValidator<NotBlankEnum,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.isEmpty();
    }
}
