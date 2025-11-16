package com.tpi.backend.requests_service.repositories;

import com.tpi.backend.requests.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
