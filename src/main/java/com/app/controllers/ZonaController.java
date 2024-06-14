package com.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/zona")
public class ZonaController {

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> crearZona(){
        return "Cualquier Cosa";
    }

    @GetMapping("/seguridad")
    @PreAuthorize("hasAnyRole('ROLE_EMPLEADO')")
    public String helloConSeguridad(){
        return "Cualquier Cosa con seguridad";
    }
}
