package com.tpi.backend.requests_service.services;

import com.tpi.backend.requests_service.dto.ContenedorRequest;
import com.tpi.backend.requests_service.dto.Ruta;
import com.tpi.backend.requests_service.models.Cliente;
import com.tpi.backend.requests_service.models.EstadoSolicitud;
import com.tpi.backend.requests_service.models.Solicitud;
import com.tpi.backend.requests_service.repositories.ClienteRepository;
import com.tpi.backend.requests_service.repositories.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SolicitudService {

    private final ClienteRepository clienteRepository;
    private final SolicitudRepository solicitudRepository;
    private final RestTemplate restTemplate;

    public Cliente validarCliente(Cliente cli) {
        return clienteRepository.save(cli);
    }

    public Long crearContenedor(Double peso, Double volumen, Long clienteId) {

        ContenedorRequest req = new ContenedorRequest(peso, volumen, clienteId);

        return restTemplate.postForObject(
                "http://containers-service/contenedores",
                req,
                Long.class
        );
    }

    public Solicitud crearSolicitud(Solicitud sol) {

        // 1) validar cliente
        Cliente cli = validarCliente(sol.getCliente());
        sol.setCliente(cli);

        // 2) crear contenedor
        Long contId = crearContenedor(
                sol.getPesoContenedor(),
                sol.getVolumenContenedor(),
                cli.getId()
        );

        sol.setContenedorId(contId);

        // 3) estado inicial
        sol.setEstado(EstadoSolicitud.BORRADOR);

        // 4) guardar
        return solicitudRepository.save(sol);
    }

    public Solicitud asignarRuta(Long solicitudId) {

        Solicitud sol = solicitudRepository.findById(solicitudId)
                .orElseThrow();

        Ruta ruta = restTemplate.postForObject(
                "http://routes-service/rutas/estimar?solicitudId=" + sol.getNumero(),
                null,
                Ruta.class
        );

        sol.setEstado(EstadoSolicitud.PROGRAMADA);

        return solicitudRepository.save(sol);
    }

    public Solicitud obtenerSolicitud(Long id) {
        return solicitudRepository.findById(id).orElseThrow();
    }

}
