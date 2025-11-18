package com.tpi.backend.requests_service.controllers;

import com.tpi.backend.requests_service.dto.SolicitudRequest;
import com.tpi.backend.requests_service.models.EstadoSolicitud;
import com.tpi.backend.requests_service.models.Solicitud;
import com.tpi.backend.requests_service.services.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;

    @PostMapping
    public Solicitud crearSolicitud(@RequestBody SolicitudRequest req) {
        return solicitudService.crearSolicitud(req);
    }

    @GetMapping("/{id}")
    public Solicitud obtener(@PathVariable Long id) {
        return solicitudService.obtenerSolicitud(id);
    }

    @PutMapping("/{id}/asignar-ruta")
    public Solicitud asignarRuta(@PathVariable Long id) {
        return solicitudService.asignarRuta(id);
    }

    @PutMapping("/{id}/asignar-camion")
    public Solicitud asignarCamion(@PathVariable Long id, @RequestParam Long camionId) {
        return solicitudService.asignarCamion(id, camionId);
    }

    @PutMapping("/{id}/entregar")
    public Solicitud entregarSolicitud(@PathVariable Long id,
            @RequestParam Double costoFinal,
            @RequestParam Integer tiempoReal) {
        return solicitudService.entregarSolicitud(id, costoFinal, tiempoReal);
    }

    @GetMapping
    public List<Solicitud> listarPorEstado(@RequestParam(required = false) String estado) {
        if (estado == null)
            return solicitudService.listarTodas();
        return solicitudService.listarPorEstado(EstadoSolicitud.valueOf(estado));
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Solicitud> listarPorCliente(@PathVariable Long clienteId) {
        return solicitudService.listarPorCliente(clienteId);
    }
}
