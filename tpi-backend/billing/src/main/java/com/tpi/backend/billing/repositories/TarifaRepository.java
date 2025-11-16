package com.tpi.backend.billing.repositories;

import com.tpi.backend.billing.models.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {}