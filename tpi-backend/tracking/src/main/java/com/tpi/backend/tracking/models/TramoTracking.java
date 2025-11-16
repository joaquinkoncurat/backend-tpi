package com.tpi.backend.tracking.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TramoTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tramoId;         // ID del tramo original del routes-service
    private Long solicitudId;     // solicitud asociada

    @Enumerated(EnumType.STRING)
    private EstadoTramo estado;

    private LocalDateTime inicioReal;
    private LocalDateTime finReal;

    private Long camionId;
}
