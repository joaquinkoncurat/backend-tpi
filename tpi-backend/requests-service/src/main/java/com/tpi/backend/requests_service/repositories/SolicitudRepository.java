package com.tpi.backend.requests_service.repositories;

import com.tpi.backend.requests_service.models.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {}

