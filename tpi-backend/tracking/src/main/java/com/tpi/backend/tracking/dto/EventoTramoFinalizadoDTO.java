package com.tpi.backend.tracking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventoTramoFinalizadoDTO {
    private Long tramoId;
    private Long rutaId;
    private Long solicitudId;
    private Double distanciaKm;
}
