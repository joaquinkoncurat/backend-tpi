package com.tpi.backend.tracking.services;

import com.tpi.backend.tracking.clients.BillingClient;
import com.tpi.backend.tracking.clients.RoutesClient;
import com.tpi.backend.tracking.dto.EventoTramoFinalizadoDTO;
import com.tpi.backend.tracking.dto.RutaResponse;
import com.tpi.backend.tracking.models.EstadoTramo;
import com.tpi.backend.tracking.models.TramoTracking;
import com.tpi.backend.tracking.repositories.EventoTrackingRepository;
import com.tpi.backend.tracking.repositories.TramoTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TramoTrackingRepository tramoRepo;
    private final EventoTrackingRepository eventoRepo;
    private final BillingClient billingClient;
    private final RoutesClient routesClient;

    public TramoTracking iniciarTramo(Long tramoId) {
        TramoTracking tramo = tramoRepo.findById(tramoId)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));

        tramo.setEstado(EstadoTramo.INICIADO);
        tramo.setInicioReal(LocalDateTime.now());
        tramoRepo.save(tramo);

        return tramo;
    }

    public TramoTracking finalizarTramo(Long tramoId, Double costoParcial) {
        TramoTracking tramo = tramoRepo.findById(tramoId)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));

        tramo.setEstado(EstadoTramo.FINALIZADO);
        tramo.setFinReal(LocalDateTime.now());
        tramoRepo.save(tramo);

        // Obtener distancia real desde Routes
        RutaResponse ruta = routesClient.obtenerRuta(tramo.getRutaId());
        double distanciaKm = ruta.getTramos().stream()
                .filter(t -> t.getId().equals(tramo.getTramoId()))
                .mapToDouble(t -> t.getDistanciaKm())
                .sum();

        // Guardar evento localmente (opcional)
        // eventoRepo.save(...)

        // Crear DTO para Billing
        EventoTramoFinalizadoDTO eventoBilling = new EventoTramoFinalizadoDTO(
                tramo.getTramoId(),
                tramo.getRutaId(),
                tramo.getSolicitudId(),
                distanciaKm);

        // Enviar evento a Billing (feign)
        billingClient.enviarEventoTramoFinalizado(eventoBilling);

        return tramo;
    }

    public List<TramoTracking> obtenerTrackingPorSolicitud(Long solicitudId) {
        return tramoRepo.findBySolicitudId(solicitudId);
    }

    public void asignarCamion(Long tramoId, Long camionId) {
        TramoTracking t = tramoRepo.findById(tramoId)
                .orElseThrow(() -> new RuntimeException("TramoTracking no encontrado: " + tramoId));

        t.setCamionId(camionId);

        tramoRepo.save(t);
    }
}
