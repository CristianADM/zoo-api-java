package com.app.controllers;

import com.app.dtos.requests.AnimalRequest;
import com.app.services.IAnimalService;
import com.app.utils.IErroresValidacion;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/animal")
@RestController
public class AnimalController {

    private final IAnimalService animalService;
    private final IErroresValidacion erroresValidacion;

    public AnimalController(IAnimalService animalService,
                             IErroresValidacion erroresValidacion) {
        this.animalService = animalService;
        this.erroresValidacion = erroresValidacion;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> crearAnimal(@Valid @RequestBody AnimalRequest animalRequest,
                                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return this.erroresValidacion.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.crearAnimal(animalRequest));
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLEADO')")
    public ResponseEntity<?> consultarTodasLosAnimales(){
        return ResponseEntity.status(HttpStatus.OK).body(animalService.consultarTodosLosAnimales());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLEADO')")
    public ResponseEntity<?> consultarZonaPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(animalService.consultarAnimalPorId(id));
    }
}
