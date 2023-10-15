package com.identity.validation;

import io.quarkus.runtime.util.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final Pattern numericPattern = Pattern.compile("\\d+");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtil.isNullOrEmpty(value)) return false;
        if (!numericPattern.matcher(value).matches()) return false;

        return value.length() == 10;
    }
}
