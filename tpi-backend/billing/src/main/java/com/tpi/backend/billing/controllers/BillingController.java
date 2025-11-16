package com.tpi.backend.billing.controllers;

import com.tpi.backend.billing.dto.EventoTramoFinalizado;
import com.tpi.backend.billing.models.Tarifa;
import com.tpi.backend.billing.services.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;

    // registrar o modificar tarifa
    @PostMapping("/tarifa")
    public Tarifa guardarTarifa(@RequestBody Tarifa tarifa) {
        return billingService.guardarTarifa(tarifa);
    }

    // obtener tarifa actual
    @GetMapping("/tarifa")
    public Tarifa obtenerTarifa() {
        return billingService.obtenerTarifaActual();
    }

    // evento enviado por tracking-service
    @PostMapping("/evento/tramo-finalizado")
    public void tramoFinalizado(@RequestBody EventoTramoFinalizado evento) {
        billingService.procesarEventoTramoFinalizado(evento);
    }

}
