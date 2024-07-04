package com.forohub.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.forohub.forohub.domain.autores.Autor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String generarJWT(Autor autor) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            String token = JWT.create()
                    .withIssuer("forohub")
                    .withSubject(autor.getUsername())
                    .withClaim("id", autor.getId())
                    .withExpiresAt(generarFechaDeExpiracion())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Invalid Signing configuration / Couldn't convert Claims");
        }
    }

    public Instant generarFechaDeExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456"); // validando firma
            verifier = JWT.require(algorithm)
                    .withIssuer("forohub")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }
}
