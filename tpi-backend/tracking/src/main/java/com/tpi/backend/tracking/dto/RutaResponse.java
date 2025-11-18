package com.tpi.backend.tracking.dto;

import lombok.Data;
import java.util.List;

@Data
public class RutaResponse {
    private Long id;
    private Long solicitudId;
    private List<TramoDTO> tramos;

    @Data
    public static class TramoDTO {
        private Long id;
        private Double distanciaKm;
        private Double peso;
        private Double volumen;
    }
}
