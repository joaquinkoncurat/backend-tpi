package com.tpi.backend.billing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.tpi.backend.billing.dto.RutaResponse;

@FeignClient(name = "routes-service", url = "http://routes-service:8082")
public interface RoutesClient {
    @GetMapping("/rutas/{id}")
    RutaResponse obtenerRuta(@PathVariable("id") Long id);
}
