package com.tpi.backend.requests_service.clients;

import com.tpi.backend.requests_service.dto.CrearTramoTrackingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "tracking-service", url = "http://tracking-service:8087")
public interface TrackingClient {
    @PostMapping("/tracking/tramos")
    void crearTramoTracking(CrearTramoTrackingDTO dto);
}
