package com.app.controllers;

import com.app.dtos.requests.EspecieRequest;
import com.app.services.IEspecieService;
import com.app.utils.IErroresValidacion;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/especie")
@RestController
public class EspeiceController {

    private final IEspecieService especieService;
    private final IErroresValidacion erroresValidacion;

    public EspeiceController(IEspecieService especieService,
                          IErroresValidacion erroresValidacion) {
        this.especieService = especieService;
        this.erroresValidacion = erroresValidacion;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> crearEspecie(@Valid @RequestBody EspecieRequest especieRequest,
                                       BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return this.erroresValidacion.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(especieService.crearEspecie(especieRequest));
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLEADO')")
    public ResponseEntity<?> consultarTodasLasEspecies(){
        return ResponseEntity.status(HttpStatus.OK).body(especieService.consultarTodasLasEspecies());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLEADO')")
    public ResponseEntity<?> consultarZonaPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(especieService.consultarEspeciePorId(id));
    }
}
