package com.tpi.backend.depots_service.controllers;

import com.tpi.backend.depots.models.Deposito;
import com.tpi.backend.depots.services.DepositoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depositos")
@RequiredArgsConstructor
public class DepositoController {

    private final DepositoService depositoService;

    @GetMapping
    public List<Deposito> obtenerTodos() {
        return depositoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Deposito obtenerPorId(@PathVariable Long id) {
        return depositoService.obtenerPorId(id);
    }

    @PostMapping
    public Deposito crear(@RequestBody Deposito d) {
        return depositoService.crear(d);
    }

}
