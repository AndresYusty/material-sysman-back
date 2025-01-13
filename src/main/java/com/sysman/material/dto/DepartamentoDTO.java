package com.sysman.material.dto;

public class DepartamentoDTO {

    private Integer codigo;
    private String nombre;

    public DepartamentoDTO() {}

    public DepartamentoDTO(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
