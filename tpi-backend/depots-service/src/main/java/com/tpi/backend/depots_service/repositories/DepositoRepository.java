package com.tpi.backend.depots_service.repositories;

import com.tpi.backend.depots.models.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {}