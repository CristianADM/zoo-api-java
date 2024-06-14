package com.app.repositories;

import com.app.entities.ZonaEntity;
import org.springframework.data.repository.CrudRepository;

public interface ZonaRepository extends CrudRepository<ZonaEntity, Long> {

    public boolean existsByNombre(String nombre);
}
