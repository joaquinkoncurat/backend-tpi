package com.tpi.backend.tracking.clients;

import com.tpi.backend.tracking.dto.EventoTramoFinalizadoDTO;
import com.tpi.backend.tracking.dto.RutaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "billing-service", url = "http://billing-service:8086")
public interface BillingClient {
    @PostMapping("/billing/eventos")
    void enviarEventoTramoFinalizado(@RequestBody EventoTramoFinalizadoDTO evento);
}

@FeignClient(name = "routes-service", url = "http://routes-service:8082")
public interface RoutesClient {
    @GetMapping("/rutas/{rutaId}")
    RutaResponse obtenerRuta(@PathVariable("rutaId") Long rutaId);
}
