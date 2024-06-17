package com.app.services;

import com.app.dtos.requests.AnimalRequest;
import com.app.dtos.responses.AnimalResponse;
import com.app.dtos.responses.EspecieResponse;
import com.app.entities.AnimalEntity;
import com.app.entities.EspecieEntity;
import com.app.repositories.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements IAnimalService {

    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Transactional
    @Override
    public AnimalResponse crearAnimal(AnimalRequest animalRequest) {
        AnimalEntity animal = AnimalEntity.builder()
                .nombre(animalRequest.getNombre())
                .especie(EspecieEntity.builder().id(animalRequest.getIdEspecie()).build())
                .build();

        animal = this.animalRepository.save(animal);

        return AnimalResponse.builder().id(animal.getId())
                .nombre(animal.getNombre())
                .especie(EspecieResponse.builder()
                        .id(animal.getEspecie().getId())
                        .nombre(animal.getEspecie().getNombre())
                        .build())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnimalResponse> consultarTodosLosAnimales() {

        List<AnimalEntity> animalesEntities = (List<AnimalEntity>) this.animalRepository.findAll();

        List<AnimalResponse> animales = new ArrayList<>();

        animalesEntities.forEach(animalEntity -> {
            animales.add(AnimalResponse.builder()
                    .id(animalEntity.getId())
                    .nombre(animalEntity.getNombre())
                    .especie(EspecieResponse.builder()
                            .id(animalEntity.getEspecie().getId())
                            .nombre(animalEntity.getEspecie().getNombre())
                            .build())
                    .build());
        });

        return animales;
    }

    @Override
    public AnimalResponse consultarAnimalPorId(Long id) {
        Optional<AnimalEntity> animalEntity = this.animalRepository.findById(id);

        return animalEntity.map(animal -> AnimalResponse.builder()
                .id(animal.getId())
                .nombre(animal.getNombre())
                .especie(EspecieResponse.builder()
                        .id(animal.getEspecie().getId())
                        .nombre(animal.getEspecie().getNombre())
                        .build())
                .build()).orElse(null);
    }

    @Override
    public boolean existsByNombreAndEspecieId(String nombre, Long idEspecie) {
        return this.animalRepository.existsByNombreAndEspecieId(nombre, idEspecie);
    }

    @Override
    public boolean existsById(Long id) {
        return this.animalRepository.existsByNombreAndEspecieId(nombre, idEspecie);
    }
}
