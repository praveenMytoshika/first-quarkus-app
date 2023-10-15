package com.identity.validation;

import io.quarkus.runtime.util.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Locale;
import java.util.Set;

public class CountryCodeAlpha3Validator implements ConstraintValidator<CountryCodeAlpha3, String> {

    private static final Set<String> isoCountries = Locale.getISOCountries(Locale.IsoCountryCode.PART1_ALPHA3);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtil.isNullOrEmpty(value)) {
            return false;
        }
        return isoCountries.contains(value);
    }
}
