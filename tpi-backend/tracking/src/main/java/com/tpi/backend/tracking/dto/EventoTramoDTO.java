package com.tpi.backend.tracking.dto;

import lombok.Data;

@Data
public class EventoTramoDTO {
    private Long tramoId;
    private Long rutaId;
    private Long solicitudId;
    private Double distanciaKm;
    private Double peso;
    private Double volumen;
}
