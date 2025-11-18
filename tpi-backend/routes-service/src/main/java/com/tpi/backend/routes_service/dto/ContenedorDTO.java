package com.tpi.backend.routes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContenedorDTO {
    private Long id;
    private Double peso;
    private Double volumen;
    private Long depositoId;
}