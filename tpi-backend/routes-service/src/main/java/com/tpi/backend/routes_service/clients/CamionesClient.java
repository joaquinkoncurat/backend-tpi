package com.tpi.backend.routes_service.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tpi.backend.routes_service.dto.CamionDTO;

@FeignClient(name = "trucks-service", url = "http://trucks-service:8083")
public interface CamionesClient {

    @GetMapping("/camiones/disponibles")
    List<CamionDTO> buscarDisponibles(@RequestParam Double peso, @RequestParam Double volumen);

    @GetMapping("/camiones/{id}")
    CamionDTO obtenerCamionPorId(@PathVariable("id") Long id);

    @PutMapping("/camiones/{id}/estado")
    void cambiarEstado(@PathVariable("id") Long id, @RequestParam String estado);
}