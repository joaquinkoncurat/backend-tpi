package com.tpi.backend.containers_service.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double peso;
    private Double volumen;

    @Enumerated(EnumType.STRING)
    private EstadoContenedor estado;

    private Long idCliente; // referencia al cliente

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EstadoContenedor estado = EstadoContenedor.DISPONIBLE;

    private Long solicitudId; // si necesitás trazar asociación
}
