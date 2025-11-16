package com.tpi.backend.requests_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tpi.backend.requests_service.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
