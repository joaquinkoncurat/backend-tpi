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

    @PostMapping("/tarifa")
    public Tarifa guardarTarifa(@RequestBody Tarifa tarifa) {
        return billingService.guardarTarifa(tarifa);
    }

    @GetMapping("/tarifa")
    public Tarifa obtenerTarifa() {
        return billingService.obtenerTarifaActual();
    }

    @PostMapping("/evento/tramo-finalizado")
    public void tramoFinalizado(@RequestBody EventoTramoFinalizado evento) {
        billingService.procesarEventoTramoFinalizado(evento);
    }

    @PostMapping("/procesar-evento")
    public void procesarEvento(@RequestBody EventoTramoFinalizado evento) {
        billingService.procesarEventoTramoFinalizado(evento);
    }

    @GetMapping("/estimacion/{rutaId}")
    public Double estimarCosto(@PathVariable Long rutaId) {
        return billingService.calcularCostoEstimado(rutaId);
    }

    @PostMapping("/billing/eventos")
    public void recibirEvento(@RequestBody EventoTramoFinalizado evento) {
        billingService.procesarEventoTramoFinalizado(evento);
    }
}
