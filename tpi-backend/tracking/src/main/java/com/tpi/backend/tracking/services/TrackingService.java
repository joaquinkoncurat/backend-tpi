package com.tpi.backend.tracking.services;

import com.tpi.backend.tracking.models.*;
import com.tpi.backend.tracking.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TramoTrackingRepository tramoRepo;
    private final EventoTrackingRepository eventoRepo;

    public TramoTracking iniciarTramo(Long tramoId) {

        TramoTracking tramo = tramoRepo.findById(tramoId).orElseThrow();

        tramo.setEstado(EstadoTramo.INICIADO);
        tramo.setInicioReal(LocalDateTime.now());

        tramoRepo.save(tramo);

        // guardar evento
        EventoTracking evt = EventoTracking.builder()
                .solicitudId(tramo.getSolicitudId())
                .tramoId(tramo.getId())
                .tipo("INICIO_TRAMO")
                .fechaHora(LocalDateTime.now())
                .build();

        eventoRepo.save(evt);

        return tramo;
    }

    public TramoTracking finalizarTramo(Long tramoId, Double costoParcial) {

        TramoTracking tramo = tramoRepo.findById(tramoId).orElseThrow();

        tramo.setEstado(EstadoTramo.FINALIZADO);
        tramo.setFinReal(LocalDateTime.now());

        tramoRepo.save(tramo);

        // evento
        EventoTracking evt = EventoTracking.builder()
                .solicitudId(tramo.getSolicitudId())
                .tramoId(tramo.getId())
                .tipo("FIN_TRAMO")
                .fechaHora(LocalDateTime.now())
                .costoParcial(costoParcial)
                .build();

        eventoRepo.save(evt);

        return tramo;
    }

    public List<TramoTracking> obtenerTrackingPorSolicitud(Long solicitudId) {
        return tramoRepo.findBySolicitudId(solicitudId);
    }

}
