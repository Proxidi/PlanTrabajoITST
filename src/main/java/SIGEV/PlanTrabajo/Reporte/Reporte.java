/*
package SIGEV.PlanTrabajo.Reporte;

import SIGEV.PlanTrabajo.Actividad.Actividad;
import SIGEV.PlanTrabajo.Evidencia.Evidencia;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Reporte")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Reporte")
    private Long idReporte;

    @Column(name = "FechaGeneracion")
    private Date fechaGeneracion;

    @Column(name = "Trimestre", nullable = false, length = 100)
    private String trimestre;

    @Column(name = "InformeActividad", nullable = false)
    private String informeActividad;

    @Column(name = "Estado", nullable = false, length = 50)
    private String estado;

    // -----RELACIONES----- //
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ActividadId")
    @JsonIgnoreProperties("reportes")
    private Actividad actividad;

    @OneToMany(mappedBy = "reporte", cascade = CascadeType.ALL)
    private List<Evidencia> evidencias = new ArrayList<>();
}
 */