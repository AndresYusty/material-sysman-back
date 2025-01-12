package com.sysman.material.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal; // Representa al usuario autenticado o información relevante
    private final String token; // El token JWT recibido en la solicitud

    // Constructor para una autenticación sin procesar (token sin validar)
    public JwtAuthenticationToken(String token) {
        super(null);
        this.token = token;
        this.principal = null; // No hay información de usuario aún
        setAuthenticated(false); // Indica que no está autenticado
    }

    // Constructor para una autenticación procesada (usuario y autoridades)
    public JwtAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.token = null; // Ya no necesitamos el token, autenticación completa
        setAuthenticated(true); // Indica que está autenticado
    }

    @Override
    public Object getCredentials() {
        return token; // Devuelve el token como credencial
    }

    @Override
    public Object getPrincipal() {
        return principal; // Devuelve el usuario autenticado o información relevante
    }
}
