package com.tpi.backend.billing.dto;

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
        private Double volumen;
        private Double peso;
    }
}
