package com.sysman.material.controller;

import com.sysman.material.dto.CiudadDTO;
import com.sysman.material.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @GetMapping
    public List<CiudadDTO> obtenerCiudades() {
        return ciudadService.obtenerTodas();
    }
}
