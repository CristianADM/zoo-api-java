package com.app.services;

import com.app.dtos.requests.ZonaRequest;
import com.app.dtos.responses.ZonaResponse;
import com.app.entities.ZonaEntity;
import com.app.repositories.ZonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaServiceImpl implements IZonaService {

    private final ZonaRepository zonaRepository;

    public ZonaServiceImpl(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    @Transactional
    @Override
    public ZonaResponse crearZona(ZonaRequest zonaRequest) {
        ZonaEntity zona = new ZonaEntity();
        zona.setNombre(zonaRequest.getNombre());

        zona = this.zonaRepository.save(zona);

        return ZonaResponse.builder()
                .id(zona.getId())
                .nombre(zona.getNombre())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existeZonaPorNombre(String nombre) {
        return zonaRepository.existsByNombre(nombre);
    }
}
