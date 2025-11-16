package com.tpi.backend.routes_service.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long solicitudId; // referencia al microservicio requests

    private Integer cantidadTramos;
    private Integer cantidadDepositos;

    @OneToMany
    private List<Tramo> tramos;
}
