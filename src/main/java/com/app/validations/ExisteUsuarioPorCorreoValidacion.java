package com.app.validations;

import com.app.services.IUsuarioService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExisteUsuarioPorCorreoValidacion implements ConstraintValidator<ExisteUsuarioPorCorreo, String> {

    private final IUsuarioService usuarioService;

    public ExisteUsuarioPorCorreoValidacion(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !usuarioService.existsByCorreo(value);
    }
}
