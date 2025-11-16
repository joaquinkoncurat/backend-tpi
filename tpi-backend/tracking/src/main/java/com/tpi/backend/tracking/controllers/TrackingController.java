package com.tpi.backend.tracking.controllers;

import com.tpi.backend.tracking.models.TramoTracking;
import com.tpi.backend.tracking.services.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tramos")
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingService trackingService;

    @PutMapping("/{id}/iniciar")
    public TramoTracking iniciar(@PathVariable Long id) {
        return trackingService.iniciarTramo(id);
    }

    @PutMapping("/{id}/finalizar")
    public TramoTracking finalizar(@PathVariable Long id) {
        return trackingService.finalizarTramo(id);
    }

    @GetMapping("/tracking/solicitud/{id}")
    public Object obtenerTrackingSolicitud(@PathVariable Long id) {
        return trackingService.obtenerTrackingPorSolicitud(id);
    }
}
