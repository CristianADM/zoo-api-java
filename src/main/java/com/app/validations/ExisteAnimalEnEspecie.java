package com.app.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Constraint(validatedBy = ExisteAnimalEnEspecieValidacion.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteAnimalEnEspecie {

    String message() default "El nombre del animal ya existe para esta especie";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
