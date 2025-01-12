package com.sysman.material.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            // Obtiene el token desde el encabezado Authorization
            String token = getTokenFromRequest(request);

            if (token != null && jwtTokenProvider.validarToken(token)) {
                // Obtiene el username del token
                String username = jwtTokenProvider.obtenerUsernameDelToken(token);

                // Carga los detalles del usuario desde el UserDetailsService
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Verifica que el contexto de seguridad no esté ya autenticado
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Crea la autenticación basada en el token
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Establece la autenticación en el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            // Maneja cualquier excepción que ocurra durante el filtro
            logger.error("No se pudo autenticar al usuario: " + ex.getMessage());

        }

        // Continúa con el siguiente filtro
        chain.doFilter(request, response);
    }

    /**
     * Obtiene el token JWT desde el encabezado Authorization.
     *
     * @param request La solicitud HTTP.
     * @return El token JWT si está presente y es válido; de lo contrario, null.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
