package com.tpi.backend.tracking.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long solicitudId;
    private Long tramoId;
    private String tipo;
    private LocalDateTime fechaHora;
    private Double costoParcial;
}
