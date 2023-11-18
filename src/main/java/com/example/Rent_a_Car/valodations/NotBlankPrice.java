package com.example.Rent_a_Car.valodations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( {ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = NotBlankPriceValidator.class)
public @interface NotBlankPrice {
    String message() default "The field cannot be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
