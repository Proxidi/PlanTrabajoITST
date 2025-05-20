package SIGEV.PlanTrabajo.Objetivo;

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
@Table(name = "Objetivo")
public class Objetivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Objetivo")
    private Long idObjetivo;

    @Column(name = "NombreObjetivo", nullable = false, length = 15)
    private String nombreObjetivo;

    @Column(name = "Estrategia", nullable = false, length = 75)
    private String estrategia;

    @Column(name = "DefEstrategia", nullable = false, length = 55)
    private String defEstrategia;

    // -----RELACIONES----- //
    @OneToMany(mappedBy = "objetivo", cascade = CascadeType.ALL)
    private List<Actividad> actividades = new ArrayList<>();
}
