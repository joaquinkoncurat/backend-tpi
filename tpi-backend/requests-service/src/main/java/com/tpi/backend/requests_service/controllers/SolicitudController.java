package com.tpi.backend.requests_service.controllers;

import com.tpi.backend.requests_service.models.Solicitud;
import com.tpi.backend.requests_service.services.SolicitudService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;

    @PostMapping
    public Solicitud crear(@RequestBody Solicitud solicitud) {
        return solicitudService.crearSolicitud(solicitud);
    }

    @GetMapping("/{id}")
    public Solicitud obtener(@PathVariable Long id) {
        return solicitudService.obtenerSolicitud(id);
    }

    @PutMapping("/{id}/asignar-ruta")
    public Solicitud asignarRuta(@PathVariable Long id) {
        return solicitudService.asignarRuta(id);
    }
}
