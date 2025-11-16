package com.tpi.backend.trucks_service.controllers;

import com.tpi.backend.trucks_service.models.Camion;
import com.tpi.backend.trucks_service.models.EstadoCamion;
import com.tpi.backend.trucks_service.services.CamionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/camiones")
@RequiredArgsConstructor
public class CamionController {

    private final CamionService camionService;

    @PostMapping
    public Long crear(@RequestBody Camion c) {
        return camionService.crear(c).getId();
    }

    @GetMapping("/{id}")
    public Camion obtener(@PathVariable Long id) {
        return camionService.obtener(id);
    }

    @GetMapping("/disponibles")
    public List<Camion> buscarDisponibles(
            @RequestParam Double peso,
            @RequestParam Double volumen
    ) {
        return camionService.buscarDisponibles(peso, volumen);
    }

    @PutMapping("/{id}/estado")
    public Camion cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoCamion estado
    ) {
        return camionService.cambiarEstado(id, estado);
    }

}
