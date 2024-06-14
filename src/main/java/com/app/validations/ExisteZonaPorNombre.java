package com.app.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExisteZonaPorNombreValidacion.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteZonaPorNombre {
    String message() default "Existe una zona registrada con ese nombre";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
