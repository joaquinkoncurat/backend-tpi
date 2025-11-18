package com.tpi.backend.requests_service.clients;

import com.tpi.backend.requests_service.dto.RutaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "routes-service", url = "http://routes-service:8082")
public interface RoutesClient {

    @PostMapping("/api/rutas/{solicitudId}")
    RutaResponse estimarRuta(@PathVariable Long solicitudId);

    @PutMapping("/api/rutas/{rutaId}/asignar-camion")
    void asignarCamion(@PathVariable Long rutaId, @RequestParam Long camionId);
}
