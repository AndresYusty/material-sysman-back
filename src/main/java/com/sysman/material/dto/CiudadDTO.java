package com.sysman.material.dto;

public class CiudadDTO {

    private Integer codigo;
    private String nombre;
    private DepartamentoDTO departamento;

    public CiudadDTO() {}

    public CiudadDTO(Integer codigo, String nombre, DepartamentoDTO departamento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.departamento = departamento;
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

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }
}
