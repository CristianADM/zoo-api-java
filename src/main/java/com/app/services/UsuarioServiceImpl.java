package com.app.services;

import com.app.dtos.requests.UsuarioRequest;
import com.app.dtos.responses.UsuarioResponse;
import com.app.entities.RolesEntity;
import com.app.entities.UserEntity;
import com.app.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UserRepository userRepository;
    private final IRolService roleService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UserRepository userRepository,
                              IRolService roleService,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest usuarioRequest) {
        String correo = usuarioRequest.getCorreo();
        String contrasenna = usuarioRequest.getContrasenna();

        Set<RolesEntity> roles = this.roleService.consultarRolesPorNombre("ROLE_EMPLEADO").stream().collect(Collectors.toSet());

        UserEntity userEntity = new UserEntity();
        userEntity.setCorreo(correo);
        userEntity.setContrasenna(passwordEncoder.encode(contrasenna));
        userEntity.setRoles(roles);

        userEntity = this.userRepository.save(userEntity);

        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(userEntity.getId());
        usuarioResponse.setCorreo(userEntity.getCorreo());
        usuarioResponse.setEstadoActivo(userEntity.getEstadoActivo());
        usuarioResponse.setFechaCreacion(userEntity.getFechaCreacion());
        usuarioResponse.setFechaModificacion(userEntity.getFechaModificacion());

        return  usuarioResponse;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserEntity> findUserEntityByCorreo(String correo) {
        return this.userRepository.findUserEntityByCorreo(correo);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByCorreo(String correo) {
        return userRepository.existsByCorreo(correo);
    }
}
