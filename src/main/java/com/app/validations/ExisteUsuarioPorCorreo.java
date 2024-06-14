package com.app.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExisteUsuarioPorCorreoValidacion.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteUsuarioPorCorreo {
    String message() default "Existe un usuario registrado con ese correo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
