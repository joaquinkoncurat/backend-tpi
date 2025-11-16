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
    private Long tramoId; // mismo ID que en routes-service (comunicación)

    @Enumerated(EnumType.STRING)
    private EstadoTramo estado;

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
}
