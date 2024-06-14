package com.app.services;

import com.app.dtos.requests.UsuarioRequest;
import com.app.dtos.responses.UsuarioResponse;
import com.app.entities.UserEntity;

import java.util.Optional;

public interface IUsuarioService {

    public UsuarioResponse crearUsuario(UsuarioRequest usuarioRequest);

    public Optional<UserEntity> findUserEntityByCorreo(String correo);

    public boolean existsByCorreo(String correo);
}
