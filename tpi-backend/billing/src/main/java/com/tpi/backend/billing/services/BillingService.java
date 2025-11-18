package com.tpi.backend.billing.services;

import com.tpi.backend.billing.clients.RequestsClient;
import com.tpi.backend.billing.clients.RoutesClient;
import com.tpi.backend.billing.dto.EventoTramoFinalizado;
import com.tpi.backend.billing.dto.RutaResponse;
import com.tpi.backend.billing.models.Tarifa;
import com.tpi.backend.billing.repositories.TarifaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final TarifaRepository tarifaRepository;
    private final RequestsClient requestsClient;
    private final RoutesClient routesClient;

    public Tarifa guardarTarifa(Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    public Tarifa obtenerTarifaActual() {
        return tarifaRepository.findAll().stream().findFirst().orElse(null);
    }

    public void procesarEventoTramoFinalizado(EventoTramoFinalizado evento) {

        Tarifa tarifa = obtenerTarifaActual();
        if (tarifa == null)
            return;

        Double costoReal = evento.getDistanciaKm() * tarifa.getCostoPorKmBase();

        System.out.println("FIN TRAMO: " + evento.getTramoId() +
                " | KM: " + evento.getDistanciaKm() +
                " | COSTO: $" + costoReal);
    }

    public Double calcularCostoEstimado(Long rutaId) {
        RutaResponse ruta = routesClient.obtenerRuta(rutaId);
        Tarifa tarifa = obtenerTarifaActual();
        if (tarifa == null)
            return null;

        Double totalKm = ruta.getTramos().stream()
                .mapToDouble(RutaResponse.TramoDTO::getDistanciaKm)
                .sum();

        return totalKm * tarifa.getCostoPorKmBase();
    }
}
