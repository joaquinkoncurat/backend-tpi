package com.tpi.backend.routes_service.services;

import com.tpi.backend.routes_service.config.GoogleMapsProperties;
import com.tpi.backend.routes_service.dto.GoogleDistanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GoogleMapsService {
    private final RestTemplate restTemplate;
    private final GoogleMapsProperties googleMapsProperties;

    public Double calcularDistanciaKm(Double lat1, Double lon1, Double lat2, Double lon2) {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json" +
                "?origins=" + lat1 + "," + lon1 +
                "&destinations=" + lat2 + "," + lon2 +
                "&key=" + googleMapsProperties.getApiKey();

        GoogleDistanceResponse resp = restTemplate.getForObject(url, GoogleDistanceResponse.class);

        if (resp == null || resp.getRows() == null || resp.getRows().isEmpty())
            throw new RuntimeException("Error al consultar Google Distance Matrix");

        Long metros = resp.getRows().get(0).getElements().get(0).getDistance().getValue();
        return metros / 1000.0;
    }
}
