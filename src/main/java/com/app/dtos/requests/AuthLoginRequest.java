package com.app.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthLoginRequest {

    @Email
    private String correo;

    @NotBlank
    private String contrasenna;
}
