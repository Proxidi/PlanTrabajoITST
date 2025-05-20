package SIGEV.PlanTrabajo.Componente;

import SIGEV.PlanTrabajo.Actividad.Actividad;
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
@Table(name = "Componente")
public class Componente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Componente")
    private Long idComponente;

    @Column(name = "NombreComponente", nullable = false, length = 15)
    private String nombreComponente;

    @Column(name = "Nivel", nullable = false, length = 15)
    private String nivel;

    @Column(name = "Indicador", nullable = false, length = 160)
    private String indicador;

    // -----RELACIONES----- //
    @OneToMany(mappedBy = "componente", cascade = CascadeType.ALL)
    private List<Actividad> actividades = new ArrayList<>();
}
