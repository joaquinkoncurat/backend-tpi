package com.tpi.backend.containers_service.controllers;

import com.tpi.backend.containers_service.models.Contenedor;
import com.tpi.backend.containers_service.models.EstadoContenedor;
import com.tpi.backend.containers_service.services.ContenedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contenedores")
@RequiredArgsConstructor
public class ContenedorController {

    private final ContenedorService contenedorService;

    // POST /contenedores
    @PostMapping
    public Long crear(@RequestBody Contenedor c) {
        return contenedorService.crear(c).getId();
    }

    // GET /contenedores/{id}
    @GetMapping("/{id}")
    public Contenedor obtener(@PathVariable Long id) {
        return contenedorService.obtener(id);
    }

    // PUT /contenedores/{id}/estado?estado=ASIGNADO
    @PutMapping("/{id}/estado")
    public Contenedor cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoContenedor estado
    ) {
        return contenedorService.cambiarEstado(id, estado);
    }

}
