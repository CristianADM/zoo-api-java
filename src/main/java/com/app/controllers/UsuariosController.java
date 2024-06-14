package com.app.controllers;

import com.app.dtos.requests.UsuarioRequest;
import com.app.dtos.responses.UsuarioResponse;
import com.app.entities.RolesEntity;
import com.app.entities.UserEntity;
import com.app.services.IRolService;
import com.app.services.IUsuarioService;
import com.app.utils.IErroresValidacion;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuariosController {

    private final IUsuarioService usuarioService;
    private final IErroresValidacion erroresValidacion;

    public UsuariosController(IUsuarioService usuarioService,
                              IErroresValidacion erroresValidacion) {
        this.usuarioService = usuarioService;
        this.erroresValidacion = erroresValidacion;
    }

    @PostMapping("/registro-empleados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> registrarEmpleado(@Valid @RequestBody UsuarioRequest usuarioRequest,
                                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return this.erroresValidacion.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(usuarioRequest));
    }
}
