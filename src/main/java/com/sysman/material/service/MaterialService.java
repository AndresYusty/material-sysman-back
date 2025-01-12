package com.sysman.material.service;

import com.sysman.material.dto.MaterialDTO;
import com.sysman.material.entity.Material;
import com.sysman.material.enums.EstadoMaterial;
import com.sysman.material.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    // Obtener todos los materiales
    public List<MaterialDTO> obtenerTodos() {
        return materialRepository.findAll()
                .stream()
                .map(this::convertirEntidadADto)
                .collect(Collectors.toList());
    }

    // Buscar materiales por tipo y rango de fechas
    public List<MaterialDTO> buscarPorTipoYFecha(String tipo, LocalDate fechaInicio, LocalDate fechaFin) {
        return materialRepository.findByTipoAndFechaCompraBetween(tipo, fechaInicio, fechaFin)
                .stream()
                .map(this::convertirEntidadADto)
                .collect(Collectors.toList());
    }

    // Buscar materiales por ciudad
    public List<MaterialDTO> buscarPorCiudad(Integer ciudadCodigo) {
        return materialRepository.findByCiudadCodigo(ciudadCodigo)
                .stream()
                .map(this::convertirEntidadADto)
                .collect(Collectors.toList());
    }

    // Crear un nuevo material
    public MaterialDTO crearMaterial(MaterialDTO materialDTO) {
        validarFechas(materialDTO.getFechaCompra(), materialDTO.getFechaVenta());
        Material material = convertirDtoAEntidad(materialDTO);
        Material materialGuardado = materialRepository.save(material);
        return convertirEntidadADto(materialGuardado);
    }

    // Actualizar un material existente
    public MaterialDTO actualizarMaterial(Long id, MaterialDTO materialDTO) {
        Material materialExistente = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material no encontrado con id: " + id));

        validarFechas(materialDTO.getFechaCompra(), materialDTO.getFechaVenta());

        materialExistente.setNombre(materialDTO.getNombre());
        materialExistente.setDescripcion(materialDTO.getDescripcion());
        materialExistente.setTipo(materialDTO.getTipo());
        materialExistente.setPrecio(materialDTO.getPrecio());
        materialExistente.setFechaCompra(materialDTO.getFechaCompra());
        materialExistente.setFechaVenta(materialDTO.getFechaVenta());
        materialExistente.setEstado(EstadoMaterial.valueOf(materialDTO.getEstado())); // Conversión corregida
        materialExistente.setCiudadCodigo(materialDTO.getCiudadCodigo());

        Material materialActualizado = materialRepository.save(materialExistente);
        return convertirEntidadADto(materialActualizado);
    }

    // Validar fechas
    private void validarFechas(LocalDate fechaCompra, LocalDate fechaVenta) {
        if (fechaVenta != null && fechaCompra.isAfter(fechaVenta)) {
            throw new IllegalArgumentException("La fecha de compra no puede ser posterior a la fecha de venta.");
        }
    }

    // Convertir de entidad a DTO
    private MaterialDTO convertirEntidadADto(Material material) {
        MaterialDTO dto = new MaterialDTO();
        dto.setId(material.getId());
        dto.setNombre(material.getNombre());
        dto.setDescripcion(material.getDescripcion());
        dto.setTipo(material.getTipo());
        dto.setPrecio(material.getPrecio());
        dto.setFechaCompra(material.getFechaCompra());
        dto.setFechaVenta(material.getFechaVenta());
        dto.setEstado(material.getEstado().toString()); // Conversión corregida
        dto.setCiudadCodigo(material.getCiudadCodigo());
        return dto;
    }

    // Convertir de DTO a entidad
    private Material convertirDtoAEntidad(MaterialDTO dto) {
        Material material = new Material();
        material.setNombre(dto.getNombre());
        material.setDescripcion(dto.getDescripcion());
        material.setTipo(dto.getTipo());
        material.setPrecio(dto.getPrecio());
        material.setFechaCompra(dto.getFechaCompra());
        material.setFechaVenta(dto.getFechaVenta());
        material.setEstado(EstadoMaterial.valueOf(dto.getEstado())); // Conversión corregida
        material.setCiudadCodigo(dto.getCiudadCodigo());
        return material;
    }
}
