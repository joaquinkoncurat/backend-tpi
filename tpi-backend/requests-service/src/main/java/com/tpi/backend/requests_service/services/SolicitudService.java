package com.tpi.backend.requests_service.services;

import com.tpi.backend.requests_service.clients.ContainersClient;
import com.tpi.backend.requests_service.clients.RoutesClient;
import com.tpi.backend.requests_service.clients.TrackingClient;
import com.tpi.backend.requests_service.dto.*;
import com.tpi.backend.requests_service.models.*;
import com.tpi.backend.requests_service.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudService {

    private final ClienteRepository clienteRepository;
    private final SolicitudRepository solicitudRepository;
    private final ContainersClient containersClient;
    private final RoutesClient routesClient;
    private final TrackingClient trackingClient;

    // --- Validación de cliente ---
    public Cliente validarCliente(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    // --- Crear contenedor ---
    public Long crearContenedor(Double peso, Double volumen, Long clienteId) {
        return containersClient.crearContenedor(new ContenedorRequest(peso, volumen, clienteId));
    }

    // --- Crear solicitud ---
    public Solicitud crearSolicitud(SolicitudRequest req) {
        Cliente cli = validarCliente(req.getClienteId());
        Long contId = crearContenedor(req.getPesoContenedor(), req.getVolumenContenedor(), cli.getId());

        Solicitud sol = Solicitud.builder()
                .cliente(cli)
                .contenedorId(contId)
                .estado(EstadoSolicitud.BORRADOR)
                .build();

        return solicitudRepository.save(sol);
    }

    // --- Asignar ruta ---
    public Solicitud asignarRuta(Long solicitudId) {
        Solicitud sol = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (sol.getEstado() != EstadoSolicitud.BORRADOR) {
            throw new RuntimeException("Solo solicitudes en BORRADOR pueden asignar ruta");
        }

        RutaResponse ruta = routesClient.estimarRuta(sol.getNumero());
        sol.setEstado(EstadoSolicitud.PROGRAMADA);

        // Calcular costo y tiempo estimado
        double costoEstimado = ruta.getTramos().stream()
                .mapToDouble(t -> t.getCostoAproximado() != null ? t.getCostoAproximado() : 0)
                .sum();
        int tiempoEstimado = ruta.getTramos().stream()
                .mapToInt(t -> t.getDuracionSegundos() != null ? (int) (t.getDuracionSegundos() / 60) : 0)
                .sum();

        sol.setCostoEstimado(costoEstimado);
        sol.setTiempoEstimado(tiempoEstimado);

        // Crear tracking para cada tramo
        ruta.getTramos().forEach(t -> trackingClient.crearTramoTracking(
                new CrearTramoTrackingDTO(t.getId(), ruta.getId(), sol.getNumero(), null)));

        return solicitudRepository.save(sol);
    }

    // --- Asignar camión ---
    public Solicitud asignarCamion(Long solicitudId, Long camionId) {
        Solicitud sol = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (sol.getEstado() != EstadoSolicitud.PROGRAMADA) {
            throw new RuntimeException("Solo solicitudes PROGRAMADAS pueden asignar camión");
        }

        routesClient.asignarCamion(sol.getNumero(), camionId);
        sol.setEstado(EstadoSolicitud.EN_TRANSITO);

        return solicitudRepository.save(sol);
    }

    // --- Entregar solicitud ---
    public Solicitud entregarSolicitud(Long solicitudId, Double costoFinal, Integer tiempoReal) {
        Solicitud sol = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (sol.getEstado() != EstadoSolicitud.EN_TRANSITO) {
            throw new RuntimeException("Solo solicitudes EN_TRANSITO pueden ser entregadas");
        }

        sol.setCostoFinal(costoFinal);
        sol.setTiempoReal(tiempoReal);
        sol.setEstado(EstadoSolicitud.ENTREGADA);

        return solicitudRepository.save(sol);
    }

    // --- Obtener solicitud ---
    public Solicitud obtenerSolicitud(Long id) {
        return solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    // --- Listar todas ---
    public List<Solicitud> listarTodas() {
        return solicitudRepository.findAll();
    }

    // --- Listar por estado ---
    public List<Solicitud> listarPorEstado(EstadoSolicitud estado) {
        return solicitudRepository.findByEstado(estado.name());
    }

    // --- Listar por cliente ---
    public List<Solicitud> listarPorCliente(Long clienteId) {
        return solicitudRepository.findAll().stream()
                .filter(s -> s.getCliente().getId().equals(clienteId))
                .toList();
    }
}
