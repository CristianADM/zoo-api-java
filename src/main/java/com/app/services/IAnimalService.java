package com.app.services;

import com.app.dtos.requests.AnimalRequest;
import com.app.dtos.responses.AnimalResponse;

import java.util.List;

public interface IAnimalService {

    public AnimalResponse crearAnimal(AnimalRequest animalRequest);

    public List<AnimalResponse> consultarTodosLosAnimales();

    public AnimalResponse consultarAnimalPorId(Long id);

    public boolean existsByNombreAndEspecieId(String nombre, Long idEspecie);

    public boolean existsById(Long id);
}
