package com.tpi.backend.requests_service.dto;

import lombok.Data;

@Data
public class SolicitudRequest {
    private Long clienteId;
    private Double pesoContenedor;
    private Double volumenContenedor;
}
