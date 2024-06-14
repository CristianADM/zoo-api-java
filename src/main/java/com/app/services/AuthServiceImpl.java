package com.app.services;

import com.app.dtos.requests.AuthLoginRequest;
import com.app.dtos.responses.AuthResponse;
import com.app.entities.UserEntity;
import com.app.repositories.RoleRepository;
import com.app.utils.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements UserDetailsService {

    private final IUsuarioService usuarioService;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(IUsuarioService usuarioService,
                           RoleRepository roleRepository,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = usuarioService.findUserEntityByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + "no se encuentra registrado"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles().forEach(rol -> authorityList.add(new SimpleGrantedAuthority(rol.getNombre())));

        return new User(userEntity.getCorreo(), userEntity.getContrasenna(), authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String correo = authLoginRequest.getCorreo();
        String contrasenna = authLoginRequest.getContrasenna();

        Authentication authentication = this.authenticate(correo, contrasenna);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = this.jwtUtil.createToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(accessToken);

        return authResponse;
    }

    public Authentication authenticate(String correo, String contrasenna) {
        UserDetails userDetails = this.loadUserByUsername(correo);

        if(userDetails == null) {
            throw new BadCredentialsException("Correo invalido y/o contraseña");
        }

        if(!passwordEncoder.matches(contrasenna, userDetails.getPassword())) {
            throw new BadCredentialsException("Correo invalido y/o contraseña");
        }

        return new UsernamePasswordAuthenticationToken(correo, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
