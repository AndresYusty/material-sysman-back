package com.sysman.material.repository;

import com.sysman.material.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findByTipoAndFechaCompraBetween(String tipo, LocalDate fechaInicio, LocalDate fechaFin);

    List<Material> findByCiudadCodigo(Integer ciudadCodigo);
}
