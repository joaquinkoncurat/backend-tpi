package com.tpi.backend.tracking.controllers;

import com.tpi.backend.tracking.dto.EventoDTO;
import com.tpi.backend.tracking.models.TramoTracking;
import com.tpi.backend.tracking.services.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tracking")
public class TrackingController {

    private final TrackingService trackingService;

    @PutMapping("/tramos/{id}/iniciar")
    public TramoTracking iniciar(@PathVariable Long id) {
        return trackingService.iniciarTramo(id);
    }

    @PutMapping("/tramos/{id}/finalizar")
    public TramoTracking finalizar(
            @PathVariable Long id,
            @RequestBody EventoDTO dto
    ) {
        return trackingService.finalizarTramo(id, dto.getCostoParcial());
    }

    @GetMapping("/solicitud/{solicitudId}")
    public List<TramoTracking> porSolicitud(@PathVariable Long solicitudId) {
        return trackingService.obtenerTrackingPorSolicitud(solicitudId);
    }

}
