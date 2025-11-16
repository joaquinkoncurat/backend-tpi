package com.tpi.backend.requests_service.services;

import com.tpi.backend.requests_service.clients.ContainersClient;
import com.tpi.backend.requests_service.clients.RoutesClient;
import com.tpi.backend.requests_service.dto.ContenedorRequest;
import com.tpi.backend.requests_service.dto.Ruta;
import com.tpi.backend.requests_service.models.Cliente;
import com.tpi.backend.requests_service.models.EstadoSolicitud;
import com.tpi.backend.requests_service.models.Solicitud;
import com.tpi.backend.requests_service.repositories.ClienteRepository;
import com.tpi.backend.requests_service.repositories.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolicitudService {

    private final ClienteRepository clienteRepository;
    private final SolicitudRepository solicitudRepository;
    private final ContainersClient containersClient;
    private final RoutesClient routesClient; // nuevo

    public Cliente validarCliente(Cliente cli) {
        return clienteRepository.save(cli);
    }

    public Long crearContenedor(Double peso, Double volumen, Long clienteId) {
        ContenedorRequest req = new ContenedorRequest(peso, volumen, clienteId);
        return containersClient.crearContenedor(req);
    }

    public Solicitud crearSolicitud(Solicitud sol) {

        Cliente cli = validarCliente(sol.getCliente());
        sol.setCliente(cli);

        Long contId = crearContenedor(
                sol.getPesoContenedor(),
                sol.getVolumenContenedor(),
                cli.getId()
        );
        sol.setContenedorId(contId);

        sol.setEstado(EstadoSolicitud.BORRADOR);

        return solicitudRepository.save(sol);
    }

    public Solicitud asignarRuta(Long solicitudId) {

        Solicitud sol = solicitudRepository.findById(solicitudId)
                .orElseThrow();

        Ruta ruta = routesClient.estimarRuta(sol.getNumero());

        sol.setEstado(EstadoSolicitud.PROGRAMADA);

        return solicitudRepository.save(sol);
    }

    public Solicitud obtenerSolicitud(Long id) {
        return solicitudRepository.findById(id).orElseThrow();
    }
}
