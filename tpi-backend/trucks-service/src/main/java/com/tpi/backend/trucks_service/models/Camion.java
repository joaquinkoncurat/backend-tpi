package com.tpi.backend.trucks_service.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dominio;             // patente
    private String nombreTransportista;
    private String telefono;

    private Double capacidadPeso;
    private Double capacidadVolumen;

    private Double costoKm; // si usan costo por kilometro

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EstadoCamion estado = EstadoCamion.DISPONIBLE;
}
