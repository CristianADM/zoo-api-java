package com.app.services;

import com.app.dtos.requests.ZonaRequest;
import com.app.dtos.responses.ZonaResponse;
import com.app.entities.ZonaEntity;
import com.app.repositories.ZonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    @Override
    public List<ZonaResponse> consultarTodasZonas() {
         List<ZonaEntity> zonas = (List<ZonaEntity>) this.zonaRepository.findAll();

         List<ZonaResponse> zonasResponse = new ArrayList<>();
         zonas.forEach(zona -> {
             zonasResponse.add(ZonaResponse.builder()
                     .id(zona.getId())
                     .nombre(zona.getNombre()).build());
         });
        return zonasResponse;
    }

    @Transactional(readOnly = true)
    @Override
    public ZonaResponse consultarZonaPorId(Long id) {

        Optional<ZonaEntity> zona = this.zonaRepository.findById(id);

        return zona.map(zonaEntity -> ZonaResponse.builder()
                .id(zonaEntity.getId())
                .nombre(zonaEntity.getNombre()).build()).orElse(null);
    }
}
