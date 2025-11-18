package com.tpi.backend.routes_service.models;

import lombok.*;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long solicitudId;
    private Integer cantidadTramos;
    private Integer cantidadDepositos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ruta_id")
    private List<Tramo> tramos;

}
