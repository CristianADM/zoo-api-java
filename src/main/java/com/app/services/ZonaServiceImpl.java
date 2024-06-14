package com.app.services;

import com.app.repositories.ZonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaServiceImpl implements IZonaService {

    private final ZonaRepository zonaRepository;

    public ZonaServiceImpl(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existeZonaPorNombre(String nombre) {
        return zonaRepository.existsByNombre(nombre);
    }
}
