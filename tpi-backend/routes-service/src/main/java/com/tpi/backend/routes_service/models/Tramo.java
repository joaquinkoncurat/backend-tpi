package com.tpi.backend.routes_service.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ubicacion origen;

    @ManyToOne
    private Ubicacion destino;

    @Enumerated(EnumType.STRING)
    private TipoTramo tipo;

    @Enumerated(EnumType.STRING)
    private EstadoTramo estado;

    private Double costoAproximado;
    private Double costoReal;

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    private Long camionId; // referencia al microservicio trucks
}
