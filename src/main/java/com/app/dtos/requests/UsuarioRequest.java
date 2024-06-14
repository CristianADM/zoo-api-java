package com.app.dtos.requests;

import com.app.validations.ExisteUsuarioPorCorreo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioRequest {

    @NotNull
    @NotBlank
    @ExisteUsuarioPorCorreo
    @Email
    private String correo;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 50)
    private String contrasenna;
}
