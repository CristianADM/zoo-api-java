package com.app.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZonaRequest {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String nombre;
}
