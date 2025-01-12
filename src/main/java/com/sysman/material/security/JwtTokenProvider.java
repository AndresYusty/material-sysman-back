package com.sysman.material.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    // Generar un token
    public String generarToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Validar un token
    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Firma inválida del JWT: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.out.println("Token JWT mal formado: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            System.out.println("El token JWT ha expirado: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.out.println("Token JWT no soportado: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("La cadena JWT está vacía: " + ex.getMessage());
        }
        return false;
    }

    // Obtener el nombre de usuario del token
    public String obtenerUsernameDelToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Obtener las reclamaciones (claims) del token
    public Claims obtenerClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
