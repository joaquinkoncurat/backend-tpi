package com.tpi.backend.depots_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tpi.backend.depots_service.models.Deposito;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {}