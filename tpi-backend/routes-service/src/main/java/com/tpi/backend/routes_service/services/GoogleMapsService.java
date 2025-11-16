package com.tpi.backend.routes_service.services;

import com.tpi.backend.routes_service.dto.GoogleDistanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GoogleMapsService {

    private final RestTemplate restTemplate;
    private final String API_KEY = "TU_API_KEY_AQUI";

    public Double calcularDistanciaKm(Double lat1, Double lon1, Double lat2, Double lon2) {

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json" +
                "?origins=" + lat1 + "," + lon1 +
                "&destinations=" + lat2 + "," + lon2 +
                "&key=" + API_KEY;

        GoogleDistanceResponse resp = restTemplate.getForObject(url, GoogleDistanceResponse.class);

        Long metros = resp
                .getRows().get(0)
                .getElements().get(0)
                .getDistance().getValue();

        return metros / 1000.0; // convertir a km
    }
}
