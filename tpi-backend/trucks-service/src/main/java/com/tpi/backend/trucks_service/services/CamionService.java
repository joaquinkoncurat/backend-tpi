package com.tpi.backend.trucks_service.services;

import com.tpi.backend.trucks_service.models.Camion;
import com.tpi.backend.trucks_service.models.EstadoCamion;
import com.tpi.backend.trucks_service.repositories.CamionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CamionService {
    private final CamionRepository camionRepository;

    public Camion crear(Camion c) {
        return camionRepository.save(c);
    }

    public Camion obtener(Long id) {
        return camionRepository.findById(id).orElseThrow();
    }

    public List<Camion> buscarDisponibles(Double peso, Double volumen) {
        return camionRepository
                .findByEstadoAndCapacidadPesoGreaterThanEqualAndCapacidadVolumenGreaterThanEqual(
                        EstadoCamion.DISPONIBLE, peso, volumen
                );
    }

    public Camion cambiarEstado(Long id, EstadoCamion nuevoEstado) {
        Camion c = obtener(id);
        c.setEstado(nuevoEstado);
        return camionRepository.save(c);
    }

}
