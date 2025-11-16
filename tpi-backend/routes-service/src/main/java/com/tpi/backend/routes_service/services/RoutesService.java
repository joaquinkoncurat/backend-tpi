package com.tpi.backend.routes_service.services;

import com.tpi.backend.routes_service.models.Ruta;
import com.tpi.backend.routes_service.repositories.RutaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoutesService {

    private final RutaRepository rutaRepository;

    public Ruta estimarRuta(Long solicitudId) {
        Ruta ruta = new Ruta();
        ruta.setSolicitudId(solicitudId);
        ruta.setCantidadDepositos(0);
        ruta.setCantidadTramos(0);
        return rutaRepository.save(ruta);
    }

    public Ruta asignarCamion(Long rutaId, Long camionId) {
        Ruta ruta = rutaRepository.findById(rutaId).orElseThrow();
        // acá iría la lógica real
        return rutaRepository.save(ruta);
    }
}
