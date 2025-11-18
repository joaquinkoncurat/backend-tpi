package com.tpi.backend.routes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrearTramoTrackingDTO {
    private Long tramoId;
    private Long rutaId;
    private Long solicitudId;
    private Long camionId;
}
