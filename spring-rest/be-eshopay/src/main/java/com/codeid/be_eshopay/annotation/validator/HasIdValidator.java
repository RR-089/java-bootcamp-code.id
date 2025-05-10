package com.codeid.be_eshopay.annotation.validator;

import com.codeid.be_eshopay.annotation.HasId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Method;

public class HasIdValidator implements ConstraintValidator<HasId, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            Method getIdMethod = value.getClass().getMethod("getId");
            Object idValue = getIdMethod.invoke(value);
            return idValue != null;
        } catch (Exception e) {
            return false;
        }
    }
}