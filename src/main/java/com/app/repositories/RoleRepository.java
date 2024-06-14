package com.app.repositories;

import com.app.entities.RolesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<RolesEntity, Long> {

    public List<RolesEntity> findByNombre(String nombre);
}
