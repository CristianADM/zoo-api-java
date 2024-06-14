package com.app.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${security.jwt.key.private}")
    private String privatekey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    public String createToken(Authentication authentication) {
        Algorithm algoritmo = Algorithm.HMAC256(this.privatekey);

        String username = authentication.getPrincipal().toString();

        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return JWT.create()
                .withIssuer(userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000))
                .withIssuedAt(new Date())
                .sign(algoritmo);
    }

    public DecodedJWT validarToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(this.privatekey);

            JWTVerifier verifier = JWT.require(algoritmo)
                    .withIssuer(this.userGenerator).build();

            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token invalido");
        }
    }

    public String extraerUsuario(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    public Claim getClaimEspecifico(DecodedJWT decodedJWT, String nombreClaim) {
        return decodedJWT.getClaim(nombreClaim);
    }

    public Map<String, Claim> obtenerTodosLosClaims(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();
    }
}
