package com.tpi.backend.routes_service.clients;

import com.tpi.backend.routes_service.dto.CrearTramoTrackingDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tracking-service", url = "http://tracking-service:8087")
public interface TrackingClient {

    @PostMapping("/tracking/tramos")
    void crearTramoTracking(@RequestBody CrearTramoTrackingDTO dto);

    @PostMapping("/tracking/tramos/{tramoId}/asignar-camion")
    void asignarCamionATracking(@PathVariable Long tramoId, @RequestParam Long camionId);
}