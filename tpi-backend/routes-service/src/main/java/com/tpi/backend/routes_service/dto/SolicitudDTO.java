package com.tpi.backend.routes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTO {
    private String id;
    private ContenedorDTO contenedor;
    private String direccionDestino;
    private Double latitudDestino;
    private Double longitudDestino;
}
