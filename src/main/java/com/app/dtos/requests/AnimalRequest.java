package com.app.dtos.requests;

import com.app.validations.ExisteAnimalEnEspecie;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ExisteAnimalEnEspecie
public class AnimalRequest {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String nombre;

    @NotNull
    @Min(value = 1)
    private Long idEspecie;
}
