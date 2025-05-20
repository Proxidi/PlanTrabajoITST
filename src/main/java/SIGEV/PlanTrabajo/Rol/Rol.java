package SIGEV.PlanTrabajo.Rol;

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
@Table(name = "Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Rol")
    private Long idRol;

    @Column(name = "NombreRol", nullable = false, length = 100)
    private String nombreRol;

    // -----RELACIONES----- //
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<Unidad> unidades = new ArrayList<>();
}
