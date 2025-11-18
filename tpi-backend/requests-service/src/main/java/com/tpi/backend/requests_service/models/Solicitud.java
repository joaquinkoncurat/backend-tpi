@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private Long contenedorId; // referencia al microservicio de contenedores

    @ManyToOne
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    private Double costoEstimado;
    private Double costoFinal;

    private Integer tiempoEstimado; // en minutos
    private Integer tiempoReal; // en minutos

    // Datos del destino solicitado por el cliente
    private String direccionDestino;
    private Double latitudDestino;
    private Double longitudDestino;
}
