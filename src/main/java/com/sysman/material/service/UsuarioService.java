package com.sysman.material.service;

import com.sysman.material.entity.Usuario;
import com.sysman.material.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    // Guardar un nuevo usuario
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    // Verificar si un usuario existe por su username
    public boolean existePorUsername(String username) {
        return usuarioRepository.findByUsername(username).isPresent();
    }

    // Verificar si un usuario existe por su email
    public boolean existePorEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }
}

