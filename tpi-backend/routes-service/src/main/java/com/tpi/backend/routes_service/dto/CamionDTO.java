package com.tpi.backend.routes_service.dto;

import lombok.Data;

@Data
public class CamionDTO {
    private Long id;
    private String dominio;
    private String nombreTransportista;
    private String telefono;
    private Double capacidadPeso;
    private Double capacidadVolumen;
    private Double costoKm;
}
