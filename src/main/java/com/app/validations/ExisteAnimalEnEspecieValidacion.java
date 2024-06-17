package com.app.validations;

import com.app.dtos.requests.AnimalRequest;
import com.app.services.IAnimalService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExisteAnimalEnEspecieValidacion implements ConstraintValidator<ExisteAnimalEnEspecie, AnimalRequest> {

    private final IAnimalService animalService;

    public ExisteAnimalEnEspecieValidacion(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @Override
    public boolean isValid(AnimalRequest value, ConstraintValidatorContext context) {
        return !this.animalService.existsByNombreAndEspecieId(value.getNombre(), value.getIdEspecie());
    }
}
