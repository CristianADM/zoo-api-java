package com.app.controllers;

import com.app.constants.Constantes;
import com.app.dtos.requests.ZonaRequest;
import com.app.services.IZonaService;
import com.app.utils.IErroresValidacion;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/zona")
public class ZonaController {

    private final IZonaService zonaService;
    private final IErroresValidacion erroresValidacion;

    public ZonaController(IZonaService zonaService,
                           IErroresValidacion erroresValidacion) {
        this.zonaService = zonaService;
        this.erroresValidacion = erroresValidacion;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> crearZona(@Valid @RequestBody ZonaRequest zonaRequest,
                                       BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return this.erroresValidacion.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(zonaService.crearZona(zonaRequest));
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole(" + Constantes.ROL_ADMINISTRADOR + ", " +  Constantes.ROL_EMPLEADO+ ")")
    public String helloConSeguridad(){
        return "Cualquier Cosa con seguridad";
    }
}
