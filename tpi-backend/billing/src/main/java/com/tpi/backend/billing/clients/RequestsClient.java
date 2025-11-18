package com.tpi.backend.billing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.tpi.backend.billing.dto.SolicitudResponse;

@FeignClient(name = "requests-service", url = "http://requests-service:8088")
public interface RequestsClient {
    @GetMapping("/solicitudes/{id}")
    SolicitudResponse obtenerSolicitud(@PathVariable("id") Long id);
}