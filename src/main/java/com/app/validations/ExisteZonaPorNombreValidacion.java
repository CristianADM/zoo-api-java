package com.app.validations;

import com.app.services.IZonaService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExisteZonaPorNombreValidacion implements ConstraintValidator<ExisteZonaPorNombre, String> {

    private final IZonaService zonaService;

    public ExisteZonaPorNombreValidacion(IZonaService zonaService) {
        this.zonaService = zonaService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !zonaService.existeZonaPorNombre(value);
    }
}
