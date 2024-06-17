package com.app.dtos.requests;

import com.app.entities.ZonaEntity;
import com.app.validations.NoExisteEnBD;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioRequest {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 500)
    private String cuerpo;

    private Long idAnimal;
    private Long idComentarioPadre;
}
