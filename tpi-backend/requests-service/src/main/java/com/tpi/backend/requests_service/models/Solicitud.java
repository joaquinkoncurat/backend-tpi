package com.tpi.backend.requests_service.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero; // ID de solicitud

    private Long contenedorId; // referencia al microservicio de contenedores

    @ManyToOne
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    private Double costoEstimado;
    private Double costoFinal;

    private Integer tiempoEstimado; // en minutos
    private Integer tiempoReal;     // en minutos
}
