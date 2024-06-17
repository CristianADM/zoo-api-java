package com.app.repositories;

import com.app.entities.ComentarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface ComentarioRepository extends CrudRepository<ComentarioEntity, Long> {
}
