package com.tpi.backend.routes_service.services;

import com.tpi.backend.routes_service.clients.CamionesClient;
import com.tpi.backend.routes_service.clients.SolicitudesClient;
import com.tpi.backend.routes_service.clients.TrackingClient;
import com.tpi.backend.routes_service.dto.CamionDTO;
import com.tpi.backend.routes_service.dto.CrearTramoTrackingDTO;
import com.tpi.backend.routes_service.dto.SolicitudDTO;
import com.tpi.backend.routes_service.models.*;
import com.tpi.backend.routes_service.repositories.RutaRepository;
import com.tpi.backend.routes_service.repositories.TramoRepository;
import com.tpi.backend.routes_service.repositories.UbicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutesService {

        private final RutaRepository rutaRepository;
        private final TramoRepository tramoRepository;
        private final UbicacionRepository ubicacionRepository;
        private final CamionesClient camionesClient;
        private final TrackingClient trackingClient;
        private final SolicitudesClient solicitudesClient;
        private final GoogleMapsService mapsService;

        public Ruta estimarRuta(Long solicitudId) {

                // 1. Obtener solicitud
                SolicitudDTO solicitud = solicitudesClient.obtenerSolicitudPorId(solicitudId);
                if (solicitud == null) {
                        throw new RuntimeException("Solicitud no encontrada: " + solicitudId);
                }

                // 2. Ubicación origen: donde está el contenedor
                Ubicacion origen = ubicacionRepository.findById(solicitud.getContenedor().getDepositoId())
                                .orElseThrow(() -> new RuntimeException("Depósito del contenedor no encontrado"));

                // 3. Ubicación destino: donde quiere el cliente
                Ubicacion destino = Ubicacion.builder()
                                .direccion("Destino Cliente")
                                .latitud(solicitud.getDestinoLatitud())
                                .longitud(solicitud.getDestinoLongitud())
                                .build();
                destino = ubicacionRepository.save(destino);

                List<Tramo> tramos = new ArrayList<>();
                double distanciaTotal = mapsService.calcularDistanciaKm(origen.getLatitud(), origen.getLongitud(),
                                destino.getLatitud(), destino.getLongitud());

                // 4. Reglas de negocio de tramos
                if (distanciaTotal <= 150.0) {
                        // Tramo directo
                        Tramo tramoDirecto = Tramo.builder()
                                        .origen(origen)
                                        .destino(destino)
                                        .tipo(TipoTramo.ORIGEN_DESTINO)
                                        .estado(EstadoTramo.ESTIMADO)
                                        .distanciaKm(distanciaTotal)
                                        .costoAproximado(distanciaTotal * 10)
                                        .build();
                        tramos.add(tramoRepository.save(tramoDirecto));
                } else {
                        // Tramo con depósito intermedio
                        // 1) ORIGEN_DEPOSITO
                        Ubicacion depositoIntermedio = seleccionarDepositoIntermedio(origen, destino);
                        double distanciaOrigenDeposito = mapsService.calcularDistanciaKm(origen.getLatitud(),
                                        origen.getLongitud(), depositoIntermedio.getLatitud(),
                                        depositoIntermedio.getLongitud());

                        Tramo tramo1 = Tramo.builder()
                                        .origen(origen)
                                        .destino(depositoIntermedio)
                                        .tipo(TipoTramo.ORIGEN_DEPOSITO)
                                        .estado(EstadoTramo.ESTIMADO)
                                        .distanciaKm(distanciaOrigenDeposito)
                                        .costoAproximado(distanciaOrigenDeposito * 10)
                                        .build();
                        tramos.add(tramoRepository.save(tramo1));

                        // 2) DEPOSITO_DESTINO
                        double distanciaDepositoDestino = mapsService.calcularDistanciaKm(
                                        depositoIntermedio.getLatitud(),
                                        depositoIntermedio.getLongitud(), destino.getLatitud(), destino.getLongitud());

                        Tramo tramo2 = Tramo.builder()
                                        .origen(depositoIntermedio)
                                        .destino(destino)
                                        .tipo(TipoTramo.DEPOSITO_DESTINO)
                                        .estado(EstadoTramo.ESTIMADO)
                                        .distanciaKm(distanciaDepositoDestino)
                                        .costoAproximado(distanciaDepositoDestino * 10)
                                        .build();
                        tramos.add(tramoRepository.save(tramo2));
                }

                // 5. Crear ruta
                Ruta ruta = Ruta.builder()
                                .solicitudId(solicitudId)
                                .cantidadTramos(tramos.size())
                                .cantidadDepositos(tramos.stream().map(t -> t.getDestino()).distinct().toArray().length)
                                .tramos(tramos)
                                .build();
                ruta = rutaRepository.save(ruta);

                // 6. Crear tramos en tracking
                for (Tramo t : tramos) {
                        trackingClient.crearTramoTracking(
                                        new CrearTramoTrackingDTO(t.getId(), ruta.getId(), solicitudId, null));
                }

                return ruta;
        }

        public Ruta asignarCamion(Long rutaId, Long camionId) {
                Ruta ruta = rutaRepository.findById(rutaId)
                                .orElseThrow(() -> new RuntimeException("Ruta no encontrada: " + rutaId));

                CamionDTO camion = camionesClient.obtenerCamionPorId(camionId);
                if (camion == null) {
                        throw new RuntimeException("Camión no encontrado");
                }

                // Validar capacidad para todos los tramos
                for (Tramo t : ruta.getTramos()) {
                        SolicitudDTO solicitud = solicitudesClient.obtenerSolicitudPorId(ruta.getSolicitudId());
                        if (camion.getCapacidadPeso() < solicitud.getContenedor().getPeso() ||
                                        camion.getCapacidadVolumen() < solicitud.getContenedor().getVolumen()) {
                                throw new RuntimeException("El camión no soporta peso/volumen del contenedor");
                        }

                        t.setCamionId(camionId);
                        t.setEstado(EstadoTramo.ASIGNADO);
                        tramoRepository.save(t);

                        trackingClient.asignarCamionATracking(t.getId(), camionId);
                }

                return rutaRepository.save(ruta);
        }

        public Ruta obtenerRuta(Long rutaId) {
                return rutaRepository.findById(rutaId)
                                .orElseThrow(() -> new RuntimeException("Ruta no encontrada: " + rutaId));
        }

        private Ubicacion seleccionarDepositoIntermedio(Ubicacion origen, Ubicacion destino) {
                // Selecciona el depósito más cercano al origen (ejemplo simplificado)
                return ubicacionRepository.findAll().stream()
                                .min(Comparator.comparingDouble(d -> mapsService.calcularDistanciaKm(
                                                origen.getLatitud(), origen.getLongitud(), d.getLatitud(),
                                                d.getLongitud())))
                                .orElseThrow(() -> new RuntimeException("No se encontró depósito intermedio"));
        }
}
