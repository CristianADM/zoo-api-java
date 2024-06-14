package com.app.repositories;

import com.app.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    public Optional<UserEntity> findUserEntityByCorreo(String correo);

    public boolean existsByCorreo(String correo);
}
