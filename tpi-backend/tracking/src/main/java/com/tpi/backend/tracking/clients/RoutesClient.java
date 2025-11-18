package com.tpi.backend.tracking.clients;

import com.tpi.backend.tracking.dto.RutaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "routes-service", url = "http://routes-service:8082")
public interface RoutesClient {
    @GetMapping("/rutas/{rutaId}")
    RutaResponse obtenerRuta(@PathVariable("rutaId") Long rutaId);
}
