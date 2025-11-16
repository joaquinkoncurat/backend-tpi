package com.tpi.backend.containers_service.repositories;

import com.tpi.backend.containers_service.models.Contenedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContenedorRepository extends JpaRepository<Contenedor, Long> {}