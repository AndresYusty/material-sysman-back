package com.sysman.material.service;

import com.sysman.material.dto.CiudadDTO;
import com.sysman.material.dto.DepartamentoDTO;
import com.sysman.material.entity.Ciudad;
import com.sysman.material.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    public List<CiudadDTO> obtenerTodas() {
        return ciudadRepository.findAll()
                .stream()
                .map(this::convertirEntidadADto)
                .collect(Collectors.toList());
    }

    private CiudadDTO convertirEntidadADto(Ciudad ciudad) {
        DepartamentoDTO departamentoDTO = new DepartamentoDTO(
                ciudad.getDepartamento().getCodigo(),
                ciudad.getDepartamento().getNombre()
        );

        return new CiudadDTO(
                ciudad.getCodigo(),
                ciudad.getNombre(),
                departamentoDTO
        );
    }
}
