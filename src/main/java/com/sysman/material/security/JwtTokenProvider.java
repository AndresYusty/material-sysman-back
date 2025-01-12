package com.sysman.material.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // Genera una clave segura (mínimo 512 bits para HS512)
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // Tiempo de expiración del token en milisegundos (por ejemplo, 10 horas)
    private static final long JWT_EXPIRATION = 36000000L;

    /**
     * Generar un token JWT.
     */
    public String generarToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Username como subject
                .setIssuedAt(new Date()) // Fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION)) // Fecha de expiración
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512) // Firma con clave y algoritmo
                .compact();
    }

    /**
     * Obtener el username del token.
     */
    public String obtenerUsernameDelToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Usar la misma clave para validar
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // Devolver el username
    }

    /**
     * Validar el token.
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // Usar la misma clave para validar
                    .build()
                    .parseClaimsJws(token); // Validar firma e integridad
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token inválido: " + e.getMessage());
        }
        return false;
    }
}