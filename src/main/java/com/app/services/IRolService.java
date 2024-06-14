package com.app.services;

import com.app.entities.RolesEntity;

import java.util.List;

public interface IRolService {

    public List<RolesEntity> consultarRolesPorNombre(String nombre);
}
