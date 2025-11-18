package com.tpi.backend.routes_service.clients;

import com.tpi.backend.routes_service.dto.SolicitudDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "solicitudes-service", url = "http://solicitudes-service:8084")
public interface SolicitudesClient {
    @GetMapping("/solicitudes/{id}")
    SolicitudDTO obtenerSolicitudPorId(@PathVariable("id") Long id);
}
