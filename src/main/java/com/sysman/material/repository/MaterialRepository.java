package com.sysman.material.repository;

import com.sysman.material.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByNombreCiudad(String nombreCiudad);
    List<Material> findByTipoAndFechaCompraBetween(String tipo, LocalDate fechaInicio, LocalDate fechaFin);

    @Query("SELECT m FROM Material m WHERE " +
            "(:nombreCiudad IS NULL OR m.nombreCiudad = :nombreCiudad) AND " +
            "(:tipo IS NULL OR m.tipo = :tipo) AND " +
            "(:fechaInicio IS NULL OR m.fechaCompra >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR m.fechaCompra <= :fechaFin)")
    List<Material> filtrarMateriales(@Param("nombreCiudad") String nombreCiudad,
                                     @Param("tipo") String tipo,
                                     @Param("fechaInicio") LocalDate fechaInicio,
                                     @Param("fechaFin") LocalDate fechaFin);


    @Query("SELECT m FROM Material m WHERE " +
            "(:tipo IS NULL OR m.tipo = :tipo) AND " +
            "(:fechaCompra IS NULL OR m.fechaCompra = :fechaCompra) AND " +
            "(:nombreCiudad IS NULL OR m.nombreCiudad = :nombreCiudad)")
    List<Material> findByFilters(
            @Param("tipo") String tipo,
            @Param("fechaCompra") LocalDate fechaCompra,
            @Param("nombreCiudad") String nombreCiudad
    );




}


