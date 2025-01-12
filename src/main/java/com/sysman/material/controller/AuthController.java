package com.sysman.material.controller;

import com.sysman.material.dto.UsuarioDTO;
import com.sysman.material.entity.Usuario;
import com.sysman.material.enums.RolUsuario;
import com.sysman.material.security.JwtTokenProvider;
import com.sysman.material.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Endpoint para autenticar usuarios y generar un token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");

            System.out.println("Intentando autenticar: " + username);
            System.out.println("Contraseña ingresada: " + password);

            // Autenticación
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Generar token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenProvider.generarToken(userDetails);

            System.out.println("Autenticación exitosa para: " + username);

            // Retornar token
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace(); // Agregar para ver errores en detalle
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }



    // Endpoint para registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        // Verificar si el usuario o email ya existen
        if (usuarioService.existePorUsername(usuarioDTO.getUsername())) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya existe");
        }

        if (usuarioService.existePorEmail(usuarioDTO.getEmail())) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }

        // Crear y guardar el usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(usuarioDTO.getUsername());
        nuevoUsuario.setEmail(usuarioDTO.getEmail());
        nuevoUsuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())); // Usar el password del DTO
        nuevoUsuario.setRol(RolUsuario.valueOf(usuarioDTO.getRol().toString()));

        usuarioService.guardarUsuario(nuevoUsuario);

        return ResponseEntity.ok("Usuario registrado con éxito");
    }


}
