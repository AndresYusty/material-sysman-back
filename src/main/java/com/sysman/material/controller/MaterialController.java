package com.sysman.material.controller;

import com.sysman.material.dto.MaterialDTO;
import com.sysman.material.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    // Obtener todos los materiales
    @GetMapping
    public ResponseEntity<List<MaterialDTO>> obtenerTodos() {
        List<MaterialDTO> materiales = materialService.obtenerTodos();
        return ResponseEntity.ok(materiales);
    }

    // Buscar materiales por tipo y rango de fechas
    @GetMapping("/buscar")
    public ResponseEntity<List<MaterialDTO>> buscarPorTipoYFecha(
            @RequestParam String tipo,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {
        List<MaterialDTO> materiales = materialService.buscarPorTipoYFecha(tipo, fechaInicio, fechaFin);
        return ResponseEntity.ok(materiales);
    }

    // Buscar materiales por ciudad
    @GetMapping("/ciudad/{ciudadCodigo}")
    public ResponseEntity<List<MaterialDTO>> buscarPorCiudad(@PathVariable Integer ciudadCodigo) {
        List<MaterialDTO> materiales = materialService.buscarPorCiudad(ciudadCodigo);
        return ResponseEntity.ok(materiales);
    }

    // Crear un nuevo material
    @PostMapping
    public ResponseEntity<MaterialDTO> crearMaterial(@RequestBody MaterialDTO materialDTO) {
        MaterialDTO materialCreado = materialService.crearMaterial(materialDTO);
        return ResponseEntity.ok(materialCreado);
    }

    // Actualizar un material existente
    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> actualizarMaterial(
            @PathVariable Long id,
            @RequestBody MaterialDTO materialDTO) {
        MaterialDTO materialActualizado = materialService.actualizarMaterial(id, materialDTO);
        return ResponseEntity.ok(materialActualizado);
    }
}
