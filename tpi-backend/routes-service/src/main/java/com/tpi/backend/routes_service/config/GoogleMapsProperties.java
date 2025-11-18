package com.tpi.backend.routes_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "googlemaps")
public class GoogleMapsProperties {
    private String apiKey;
}
