package com.app.services;

import com.app.dtos.requests.EspecieRequest;
import com.app.dtos.responses.EspecieResponse;

import java.util.List;

public interface IEspecieService {

    public EspecieResponse crearEspecie(EspecieRequest especieRequest);

//    public boolean existeZonaPorNombre(String nombre);

    public List<EspecieResponse> consultarTodasLasEspecies();

    public EspecieResponse consultarEspeciePorId(Long id);
}
