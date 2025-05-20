package SIGEV.PlanTrabajo.Calendarizacion;

import SIGEV.PlanTrabajo.Actividad.Actividad;
import SIGEV.PlanTrabajo.Mes.Mes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Calendarizacion")
public class Calendarizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Calendarizacion")
    private Long idCalendarizacion;

    @Column(name = "Presupuesto", nullable = false)
    private float presupuesto;

    // -----RELACIONES----- //
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MesId")
    @JsonIgnoreProperties("calendarizaciones")
    private Mes mes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ActividadId")
    @JsonIgnoreProperties("calendarizaciones")
    private Actividad actividad;
}
