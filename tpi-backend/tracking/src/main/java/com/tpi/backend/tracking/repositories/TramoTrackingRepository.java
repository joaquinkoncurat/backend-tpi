package com.tpi.backend.tracking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tpi.backend.tracking.models.TramoTracking;
import java.util.List;

public interface TramoTrackingRepository extends JpaRepository<TramoTracking, Long> {
    List<TramoTracking> findBySolicitudId(Long solicitudId);
}
