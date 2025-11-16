package com.tpi.backend.routes_service.repositories;

import com.tpi.backend.routes_service.models.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {}