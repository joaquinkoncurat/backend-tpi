package com.tpi.backend.routes_service.services;

import com.tpi.backend.routes_service.models.*;
import com.tpi.backend.routes_service.repositories.RutaRepository;
import com.tpi.backend.routes_service.repositories.TramoRepository;
import com.tpi.backend.routes_service.repositories.UbicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutesService {

    private final RutaRepository rutaRepository;
    private final TramoRepository tramoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final GoogleMapsService mapsService;
    private final CamionesClient camionesClient;

    public Ruta estimarRuta(Long solicitudId) {

        // 1) Obtener ubicaciones de origen y destino
        // En un TPI real deberían venir desde request-service
        // Por ahora: valores simulados

        Ubicacion origen = ubicacionRepository.save(
                Ubicacion.builder()
                        .direccion("Origen")
                        .latitud(-34.6037)
                        .longitud(-58.3816)
                        .build()
        );

        Ubicacion destino = ubicacionRepository.save(
                Ubicacion.builder()
                        .direccion("Destino")
                        .latitud(-34.9205)
                        .longitud(-57.9536)
                        .build()
        );

        // 2) Calcular distancia Google Maps
        Double distanciaKm = mapsService.calcularDistanciaKm(
                origen.getLatitud(), origen.getLongitud(),
                destino.getLatitud(), destino.getLongitud()
        );

        // 3) Obtener un camión disponible desde trucks-service
        Long camionId = camionesClient.obtenerCamionDisponible(1000.0, 10.0);

        // 4) Crear tramo único por ahora (Origen → Destino)
        Tramo tramo = Tramo.builder()
                .origen(origen)
                .destino(destino)
                .tipo(TipoTramo.ORIGEN_DESTINO)
                .estado(EstadoTramo.ESTIMADO)
                .costoAproximado(distanciaKm * 10) // ejemplo
                .camionId(camionId)
                .build();

        tramo = tramoRepository.save(tramo);

        List<Tramo> tramos = new ArrayList<>();
        tramos.add(tramo);

        // 5) Crear la Ruta
        Ruta ruta = Ruta.builder()
                .solicitudId(solicitudId)
                .cantidadDepositos(0)
                .cantidadTramos(1)
                .tramos(tramos)
                .build();

        return rutaRepository.save(ruta);
    }

    public Ruta asignarCamion(Long rutaId, Long camionId) {

        Ruta ruta = rutaRepository.findById(rutaId).orElseThrow();

        for (Tramo t : ruta.getTramos()) {
            t.setCamionId(camionId);
            tramoRepository.save(t);
        }

        return rutaRepository.save(ruta);
    }

}
