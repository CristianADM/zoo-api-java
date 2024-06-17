package com.app.controllers;

import com.app.dtos.requests.ComentarioRequest;
import com.app.services.IAnimalService;
import com.app.services.IComentarioService;
import com.app.utils.IErroresValidacion;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/comentario")
@RestController
public class ComentarioController {

    private final IComentarioService comentarioService;
    private final IAnimalService animalService;
    private final IErroresValidacion erroresValidacion;

    public ComentarioController(IComentarioService comentarioService,
                             IAnimalService animalService,
                             IErroresValidacion erroresValidacion) {
        this.comentarioService = comentarioService;
        this.animalService = animalService;
        this.erroresValidacion = erroresValidacion;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> crearComentario(@Valid @RequestBody ComentarioRequest comentarioRequest,
                                          BindingResult bindingResult) {
        if(comentarioRequest.getIdAnimal() != null) {
            this.animalService.
        }


        if(bindingResult.hasErrors()) {
            return this.erroresValidacion.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.crearEspecie(especieRequest));
    }
}
