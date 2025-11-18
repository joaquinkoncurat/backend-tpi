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

    private Long rutaId;
    private Long tramoId;
    private Long solicitudId;

    @Enumerated(EnumType.STRING)
    private EstadoTramo estado;

    private LocalDateTime inicioReal;
    private LocalDateTime finReal;
    private Long camionId;
}
