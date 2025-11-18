package com.tpi.backend.billing.dto;

import lombok.Data;

@Data
public class SolicitudResponse {
    private Long id;
    private Long clienteId;
    private Double pesoContenedor;
    private Double volumenContenedor;
}
