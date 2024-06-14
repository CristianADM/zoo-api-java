package com.app.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UsuarioResponse {

    private Long id;
    private String correo;
    private Boolean estadoActivo;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
