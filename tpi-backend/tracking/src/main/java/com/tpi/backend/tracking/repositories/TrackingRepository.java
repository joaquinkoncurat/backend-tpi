package com.tpi.backend.tracking.repositories;

import com.tpi.backend.tracking.models.TramoTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<TramoTracking, Long> {}
