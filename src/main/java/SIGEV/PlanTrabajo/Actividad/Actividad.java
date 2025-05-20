package SIGEV.PlanTrabajo.Actividad;

import SIGEV.PlanTrabajo.Calendarizacion.Calendarizacion;
import SIGEV.PlanTrabajo.Programa.Programa;
import SIGEV.PlanTrabajo.Componente.Componente;
import SIGEV.PlanTrabajo.Objetivo.Objetivo;
import SIGEV.PlanTrabajo.Unidad.Unidad;
import SIGEV.PlanTrabajo.UnidadMedida.UnidadMedida;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Actividad")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Actividad")
    private Long idActividad;

    @Column(name = "NoActividad", nullable = false)
    private int noActividad;

    @Column(name = "NombreActividad", nullable = false, length = 200)
    private String nombreActividad;

    @Column(name = "MedioVerificacion", nullable = false, length = 100)
    private String medioVerificacion;

    @Column(name = "IndicadorResultado", nullable = false, length = 100)
    private String indicadorResultado;

    // -----RELACIONES----- //
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ProgramaId")
    @JsonIgnoreProperties("actividades")
    private Programa programa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ObjetivoId")
    @JsonIgnoreProperties("actividades")
    private Objetivo objetivo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ComponenteId")
    @JsonIgnoreProperties("actividades")
    private Componente componente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UnidadId")
    @JsonIgnoreProperties("actividades")
    private Unidad unidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UnidadMedidaId")
    @JsonIgnoreProperties("actividades")
    private UnidadMedida unidadMedida;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
    private List<Calendarizacion> calendarizaciones = new ArrayList<>();
}
