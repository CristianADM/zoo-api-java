package com.app.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = NoExisteEnBdValidacion.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoExisteEnBD {

    String message() default "El valor no existe en la base de datos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> entity();
    String field() default "id"; // Campo a validar en la entidad
}
