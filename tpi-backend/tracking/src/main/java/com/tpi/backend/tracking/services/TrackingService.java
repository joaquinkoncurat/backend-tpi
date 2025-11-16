package com.tpi.backend.tracking.services;

import com.tpi.backend.tracking.models.EstadoTramo;
import com.tpi.backend.tracking.models.TramoTracking;
import com.tpi.backend.tracking.repositories.TrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TrackingRepository trackingRepository;

    public TramoTracking iniciarTramo(Long id) {
        TramoTracking t = new TramoTracking();
        t.setTramoId(id);
        t.setEstado(EstadoTramo.INICIADO);
        t.setFechaHoraInicio(LocalDateTime.now());
        return trackingRepository.save(t);
    }

    public TramoTracking finalizarTramo(Long id) {
        TramoTracking t = trackingRepository.findById(id).orElseThrow();
        t.setEstado(EstadoTramo.FINALIZADO);
        t.setFechaHoraFin(LocalDateTime.now());
        return trackingRepository.save(t);
    }

    public Object obtenerTrackingPorSolicitud(Long idSolicitud) {
        // por ahora devolvemos un texto hasta unir microservicios
        return "Tracking de solicitud " + idSolicitud;
    }
}
