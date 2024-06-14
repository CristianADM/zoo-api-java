package com.app.services;

import com.app.dtos.requests.ZonaRequest;
import com.app.dtos.responses.ZonaResponse;

public interface IZonaService {

    public ZonaResponse crearZona(ZonaRequest zonaRequest);

    public boolean existeZonaPorNombre(String nombre);
}
