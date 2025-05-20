/*
package SIGEV.PlanTrabajo.Evidencia;

import SIGEV.PlanTrabajo.Actividad.Actividad;
import SIGEV.PlanTrabajo.Reporte.Reporte;
import SIGEV.PlanTrabajo.Unidad.Unidad;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Evidencia")
public class Evidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Evidencia")
    private Long idEvidencia;

    @Column(name = "Descripcion", length = 100)
    private String descripcion;

    @Column(name = "FechaSubida")
    private Date fechaSubida;

    @Column(name = "Estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "Archivo", nullable = false)
    private String archivo;

    // -----RELACIONES----- //
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UnidadId")
    @JsonIgnoreProperties("evidencias")
    private Unidad unidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ActividadId")
    @JsonIgnoreProperties("evidencias")
    private Actividad actividad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ReporteId")
    @JsonIgnoreProperties("evidencias")
    private Reporte reporte;
}
 */