package com.tpi.backend.tracking.repositories;

import com.tpi.backend.tracking.models.EventoTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoTrackingRepository extends JpaRepository<EventoTracking, Long> {}