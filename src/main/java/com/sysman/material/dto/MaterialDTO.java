package com.sysman.material.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MaterialDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private BigDecimal precio;
    private LocalDate fechaCompra;
    private LocalDate fechaVenta;
    private String estado;
    private Integer ciudadCodigo;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCiudadCodigo() {
        return ciudadCodigo;
    }

    public void setCiudadCodigo(Integer ciudadCodigo) {
        this.ciudadCodigo = ciudadCodigo;
    }
}
