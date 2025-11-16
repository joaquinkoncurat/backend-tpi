package com.tpi.backend.trucks_service.repositories;

import com.tpi.backend.trucks_service.models.Camion;
import com.tpi.backend.trucks_service.models.EstadoCamion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CamionRepository extends JpaRepository<Camion, Long> {

    // buscador importante para routes-service
    List<Camion> findByEstadoAndCapacidadPesoGreaterThanEqualAndCapacidadVolumenGreaterThanEqual(
            EstadoCamion estado,
            Double peso,
            Double volumen
    );
}
