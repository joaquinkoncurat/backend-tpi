package com.tpi.backend.routes_service.repositories;

import com.tpi.backend.routes_service.models.Tramo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TramoRepository extends JpaRepository<Tramo, Long> {}