package SIGEV.PlanTrabajo.Mes;

import SIGEV.PlanTrabajo.Calendarizacion.Calendarizacion;
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
@Table(name = "Mes")
public class Mes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Mes")
    private Long idMes;

    @Column(name = "NombreMes", nullable = false, length = 11)
    private String nombreMes;

    @OneToMany(mappedBy = "mes", cascade = CascadeType.ALL)
    private List<Calendarizacion> calendarizaciones = new ArrayList<>();
}
