package com.app.services;

import com.app.entities.RolesEntity;
import com.app.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService {

    private RoleRepository roleRepository;

    public RolServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RolesEntity> consultarRolesPorNombre(String nombre) {
        return this.roleRepository.findByNombre(nombre);
    }
}
