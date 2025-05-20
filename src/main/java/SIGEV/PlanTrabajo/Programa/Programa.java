package SIGEV.PlanTrabajo.Programa;

import SIGEV.PlanTrabajo.Actividad.Actividad;
import SIGEV.PlanTrabajo.Unidad.Unidad;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Programa")
public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Programa")
    private Long idPrograma;

    @Column(name = "Año", nullable = false)
    private int anio;

    @Column(name = "UnidadResponsable", nullable = false, length = 200)
    private String unidadResponsable;

    @Column(name = "JefeUnidad", nullable = false, length = 200)
    private String jefeUnidad;

    @Column(name = "ObjetivoArea", nullable = false, length = 200)
    private String objetivoArea;

    // -----RELACIONES----- //
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UnidadId")
    @JsonIgnoreProperties("programas")
    private Unidad unidad;

    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL)
    private List<Actividad> actividades = new ArrayList<>();
}
