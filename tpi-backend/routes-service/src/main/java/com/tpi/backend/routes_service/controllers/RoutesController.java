package com.tpi.backend.routes_service.controllers;

import com.tpi.backend.routes_service.models.Ruta;
import com.tpi.backend.routes_service.services.RoutesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rutas")
@RequiredArgsConstructor
public class RoutesController {

    private final RoutesService routesService;

    @PostMapping("/estimar")
    public Ruta estimarRuta(@RequestParam Long solicitudId) {
        return routesService.estimarRuta(solicitudId);
    }

    @PostMapping("/{id}/asignar-camion")
    public Ruta asignarCamion(@PathVariable Long id, @RequestParam Long camionId) {
        return routesService.asignarCamion(id, camionId);
    }

    @GetMapping("/{id}")
    public Ruta obtenerRuta(@PathVariable Long id) {
        return routesService.obtenerRuta(id);
    }
}
