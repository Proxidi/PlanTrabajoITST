package SIGEV.PlanTrabajo.Area;

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
@Table(name = "Area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Area")
    private Long idArea;

    @Column(name = "NombreArea", nullable = false, length = 100)
    private String nombreArea;

    // -----RELACIONES----- //
    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
    private List<Unidad> unidades = new ArrayList<>();
}
