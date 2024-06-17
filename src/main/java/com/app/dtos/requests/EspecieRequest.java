package com.app.dtos.requests;

import com.app.entities.EspecieEntity;
import com.app.entities.ZonaEntity;
import com.app.validations.ExisteEnBD;
import com.app.validations.NoExisteEnBD;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EspecieRequest {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @ExisteEnBD(field = "nombre", entity = EspecieEntity.class, message = "ya esta registrado!")
    private String nombre;

    @NotNull
    @Min(value = 1)
    @NoExisteEnBD(field = "id", entity = ZonaEntity.class, message = "No existe zona con ese id")
    private Long idZona;
}
