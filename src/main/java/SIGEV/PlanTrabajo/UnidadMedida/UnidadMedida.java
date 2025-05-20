package SIGEV.PlanTrabajo.UnidadMedida;

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
@Table(name = "Unidad_Medida")
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_UnidadMedida")
    private Long idUnidadMedida;

    @Column(name = "NombreUnidadMedida", nullable = false, length = 15)
    private String nombreUnidadMedida;

    // -----RELACIONES----- //
    @OneToMany(mappedBy = "unidadMedida", cascade = CascadeType.ALL)
    private List<Actividad> actividades = new ArrayList<>();
}
