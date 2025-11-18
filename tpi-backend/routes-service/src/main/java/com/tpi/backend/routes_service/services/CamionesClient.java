package com.tpi.backend.routes_service.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tpi.backend.routes_service.dto.CamionDTO;

@FeignClient(name = "trucks-service", url = "http://trucks-service:8083")
public interface CamionesClient {

    @GetMapping("/camiones/disponible")
    Long obtenerCamionDisponible(@RequestParam Double peso, @RequestParam Double volumen);

    @GetMapping("/camiones/{id}")
    CamionDTO obtenerCamionPorId(@PathVariable("id") Long id);
}
