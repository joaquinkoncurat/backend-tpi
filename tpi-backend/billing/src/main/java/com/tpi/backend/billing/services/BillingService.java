package com.tpi.backend.billing.services;

import com.tpi.backend.billing.dto.EventoTramoFinalizado;
import com.tpi.backend.billing.models.Tarifa;
import com.tpi.backend.billing.repositories.TarifaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final TarifaRepository tarifaRepository;

    public Tarifa guardarTarifa(Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    public Tarifa obtenerTarifaActual() {
        return tarifaRepository.findAll().stream().findFirst().orElse(null);
    }

    public void procesarEventoTramoFinalizado(EventoTramoFinalizado evento) {
        // Por ahora solo mostrar en log
        System.out.println("Tramo finalizado: " + evento.getTramoId()
                         + " | Costo: " + evento.getCostoReal());
        // Más adelante: guardar costos intermedios
    }

    public Double calcularCostoEstimado(Double distanciaKm) {
        Tarifa tarifa = obtenerTarifaActual();
        if (tarifa == null) return null;

        Double costoKm = distanciaKm * tarifa.getCostoPorKmBase();
        return costoKm;
    }
}
