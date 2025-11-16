package com.tpi.backend.requests_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContenedorRequest {
    private Double peso;
    private Double volumen;
    private Long clienteId;
}
