package com.tpi.backend.billing.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double costoPorKmBase;
    private Double costoLitroCombustible;
    private Double costoEstadiaPorDia;
}
