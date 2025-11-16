package com.tpi.backend.requests_service.clients;

import com.tpi.backend.requests_service.dto.Ruta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "routes-service", url = "http://localhost:8085") // cambiar puerto
public interface RoutesClient {

    @PostMapping("/rutas/estimar")
    Ruta estimarRuta(@RequestParam Long solicitudId);
}
