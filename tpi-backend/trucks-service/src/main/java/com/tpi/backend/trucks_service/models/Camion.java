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

    private String dominio; // patente
    private String nombreTransportista;
    private String telefono;

    private Double capacidadPeso;
    private Double capacidadVolumen;

    private Double costoBaseKm;
    private Double consumoCombustible; // litros por km

    @Enumerated(EnumType.STRING)
    private EstadoCamion estado;
}
