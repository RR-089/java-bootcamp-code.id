package com.codeid.be_eshopay.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HasIdValidator.class)
public @interface HasId {
    String message() default "The 'id' field must not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class HasIdValidator implements ConstraintValidator<HasId, Object> {
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
