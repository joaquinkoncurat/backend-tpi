package com.tpi.backend.routes_service.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "trucks-service", url = "http://localhost:8086")
public class CamionesClient {

    @GetMapping("/camiones/disponible")
    Long obtenerCamionDisponible(
            @RequestParam Double peso,
            @RequestParam Double volumen
    );
}
