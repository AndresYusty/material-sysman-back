package com.sysman.material.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implementar la lógica para buscar el usuario en la base de datos.
        // Por ejemplo:
        if ("admin".equals(username)) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username("admin")
                    .password("{noop}password") // {noop} indica que no se aplicará cifrado a la contraseña
                    .roles("ADMIN") // Define los roles del usuario
                    .build();
        }
        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}
