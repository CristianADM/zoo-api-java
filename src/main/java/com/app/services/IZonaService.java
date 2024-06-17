package com.app.services;

import com.app.dtos.requests.ZonaRequest;
import com.app.dtos.responses.ZonaResponse;

import java.util.List;

public interface IZonaService {

    public ZonaResponse crearZona(ZonaRequest zonaRequest);

    public boolean existeZonaPorNombre(String nombre);

    public List<ZonaResponse> consultarTodasZonas();

    public ZonaResponse consultarZonaPorId(Long id);
}
