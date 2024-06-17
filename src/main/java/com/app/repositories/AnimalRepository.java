package com.app.repositories;

import com.app.entities.AnimalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AnimalRepository extends CrudRepository<AnimalEntity, Long> {

    public boolean existsById(Long id);

    @Query("SELECT COUNT(a) > 0 FROM AnimalEntity a WHERE a.nombre = :nombre AND a.especie.id = :especieId")
    public boolean existsByNombreAndEspecieId(@Param("nombre") String nombre, @Param("especieId") Long especieId);
}
