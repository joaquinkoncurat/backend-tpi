package com.tpi.backend.requests_service.services;

import com.tpi.backend.requests_service.models.EstadoSolicitud;
import com.tpi.backend.requests_service.models.Solicitud;
import com.tpi.backend.requests_service.repositories.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolicitudService {

    private final ClienteRepository clienteRepository;
    private final RestTemplate restTemplate;
    private final SolicitudRepository solicitudRepository;

    public Cliente validarCliente(Cliente cli) {
        // si el cliente ya existe (tiene ID) lo actualizamos
        // si no tiene ID, lo creamos
        return clienteRepository.save(cli);
    }

    public Long crearContenedor(Double peso, Double volumen, Long clienteId) {

        // objeto para enviar al microservicio containers
        ContenedorRequest req = new ContenedorRequest(peso, volumen, clienteId);

        Long contenedorId = restTemplate.postForObject(
            "http://containers-service/contenedores",
            req,
            Long.class
        );

        return contenedorId;
    }
    


    public Solicitud crearSolicitud(Solicitud sol) {

        // 1) validar cliente
        Cliente cli = validarCliente(sol.getCliente());
        sol.setCliente(cli);

        // 2) crear contenedor llamando al microservicio containers
        Long contId = crearContenedor(
            sol.getPesoContenedor(),
            sol.getVolumenContenedor(),
            cli.getId()
        );
        sol.setContenedorId(contId);

        // 3) estado inicial
        sol.setEstado(EstadoSolicitud.BORRADOR);

        // 4) guardar en DB
        return solicitudRepository.save(sol);
    }


    public Solicitud obtenerSolicitud(Long id) {
        return solicitudRepository.findById(id).orElseThrow();
    }

    public Solicitud asignarRuta(Long id) {
        Solicitud sol = obtenerSolicitud(id);
        sol.setEstado(EstadoSolicitud.PROGRAMADA);
        return solicitudRepository.save(sol);
    }

}
