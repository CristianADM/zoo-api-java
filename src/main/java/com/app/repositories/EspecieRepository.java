package com.app.repositories;

import com.app.entities.EspecieEntity;
import org.springframework.data.repository.CrudRepository;

public interface EspecieRepository extends CrudRepository<EspecieEntity, Long> {
}
