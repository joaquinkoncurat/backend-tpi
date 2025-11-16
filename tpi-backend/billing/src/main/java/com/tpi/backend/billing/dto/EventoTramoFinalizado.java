package com.tpi.backend.billing.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoTramoFinalizado {
    private Long tramoId;
    private Double costoReal;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
}
