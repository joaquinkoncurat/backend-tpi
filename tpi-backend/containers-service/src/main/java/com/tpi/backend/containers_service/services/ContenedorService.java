package com.tpi.backend.containers_service.services;

import com.tpi.backend.containers_service.models.Contenedor;
import com.tpi.backend.containers_service.models.EstadoContenedor;
import com.tpi.backend.containers_service.repositories.ContenedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContenedorService {

    private final ContenedorRepository contenedorRepository;

    public Contenedor crear(Contenedor c) {
        // estado inicial siempre DISPONIBLE
        c.setEstado(EstadoContenedor.DISPONIBLE);
        return contenedorRepository.save(c);
    }

    public Contenedor obtener(Long id) {
        return contenedorRepository.findById(id).orElseThrow();
    }

    public Contenedor cambiarEstado(Long id, EstadoContenedor nuevoEstado) {
        Contenedor c = obtener(id);
        c.setEstado(nuevoEstado);
        return contenedorRepository.save(c);
    }

}
