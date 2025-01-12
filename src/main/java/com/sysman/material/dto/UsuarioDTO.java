package com.sysman.material.dto;

import com.sysman.material.enums.RolUsuario;

public class UsuarioDTO {

    private String username;
    private String email;
    private RolUsuario rol;
    private String password; // Añadir este campo

    public UsuarioDTO() {}

    public UsuarioDTO(String username, String email, RolUsuario rol, String password) {
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public String getPassword() { // Agregar el getter
        return password;
    }

    public void setPassword(String password) { // Agregar el setter
        this.password = password;
    }
}
