package com.tpi.backend.routes_service.repositories;

import com.tpi.backend.routes_service.models.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RutaRepository extends JpaRepository<Ruta, Long> {}