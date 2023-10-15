package com.identity.validation;



import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CountryCodeAlpha3Validator.class)
public @interface CountryCodeAlpha3 {
    String message() default "Invalid Country Code. Must be ISO 3166-1 Alpha-3 code.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}