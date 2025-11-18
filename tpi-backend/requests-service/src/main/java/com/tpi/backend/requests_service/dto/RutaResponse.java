package com.tpi.backend.requests_service.dto;

import lombok.Data;
import java.util.List;

@Data
public class RutaResponse {

    private Long id;
    private Long solicitudId;
    private Integer cantidadTramos;
    private Integer cantidadDepositos;
    private List<TramoResponse> tramos;

    @Data
    public static class TramoResponse {
        private Long id;
        private String tipoTramo;
        private String estado;
        private Long distanciaMetros;
        private Long duracionSegundos;
        private Double costoAproximado;
    }
}
