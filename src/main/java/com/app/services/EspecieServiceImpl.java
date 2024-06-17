package com.app.services;

import com.app.dtos.requests.EspecieRequest;
import com.app.dtos.responses.EspecieResponse;
import com.app.dtos.responses.ZonaResponse;
import com.app.entities.EspecieEntity;
import com.app.entities.ZonaEntity;
import com.app.repositories.EspecieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EspecieServiceImpl implements IEspecieService {

    private final EspecieRepository especieRepository;

    public EspecieServiceImpl(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }

    @Transactional
    @Override
    public EspecieResponse crearEspecie(EspecieRequest especieRequest) {

        EspecieEntity especie = EspecieEntity.builder()
                .nombre(especieRequest.getNombre())
                .zona(ZonaEntity.builder().id(especieRequest.getIdZona()).build())
                .build();

        especie = this.especieRepository.save(especie);

        return EspecieResponse.builder()
                .id(especie.getId())
                .nombre(especie.getNombre())
                .zona(ZonaResponse.builder()
                        .id(especie.getZona().getId())
                        .nombre(especie.getZona().getNombre())
                        .build())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<EspecieResponse> consultarTodasLasEspecies() {
        List<EspecieEntity> especiesEntities = (List<EspecieEntity>) this.especieRepository.findAll();

        List<EspecieResponse> especies = new ArrayList<>();
        especiesEntities.forEach(especie -> {
            especies.add(EspecieResponse.builder()
                    .id(especie.getId())
                    .nombre(especie.getNombre())
                    .zona(ZonaResponse.builder()
                            .id(especie.getZona().getId())
                            .nombre(especie.getZona().getNombre())
                            .build())
                    .build());
        });
        return especies;
    }

    @Override
    public EspecieResponse consultarEspeciePorId(Long id) {

        Optional<EspecieEntity> especie = this.especieRepository.findById(id);

        return especie.map(especieEntity -> EspecieResponse.builder()
                    .id(especieEntity.getId())
                    .nombre(especieEntity.getNombre())
                    .zona(ZonaResponse.builder()
                            .id(especieEntity.getZona().getId())
                            .nombre(especieEntity.getZona().getNombre())
                            .build())
                    .build()).orElse(null);
    }
}
