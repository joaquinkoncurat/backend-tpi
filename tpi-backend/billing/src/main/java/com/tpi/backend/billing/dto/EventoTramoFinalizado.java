package com.tpi.backend.billing.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventoTramoFinalizado {
    private Long tramoId;
    private Long rutaId;
    private Long solicitudId;
    private Double costoReal;
    private Double distanciaKm;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
}
