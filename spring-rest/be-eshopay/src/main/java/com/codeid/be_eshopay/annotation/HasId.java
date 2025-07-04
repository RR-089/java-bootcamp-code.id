package com.codeid.be_eshopay.annotation;

import com.codeid.be_eshopay.annotation.validator.HasIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HasIdValidator.class)
public @interface HasId {
    String message() default "The 'id' field must not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


