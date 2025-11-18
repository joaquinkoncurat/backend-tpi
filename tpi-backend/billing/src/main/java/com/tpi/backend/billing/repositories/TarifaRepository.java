package com.tpi.backend.billing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tpi.backend.billing.models.Tarifa;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {}