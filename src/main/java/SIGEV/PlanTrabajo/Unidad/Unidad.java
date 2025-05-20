package SIGEV.PlanTrabajo.Unidad;

import SIGEV.PlanTrabajo.Actividad.Actividad;
import SIGEV.PlanTrabajo.Area.Area;
import SIGEV.PlanTrabajo.Programa.Programa;
import SIGEV.PlanTrabajo.Rol.Rol;
import SIGEV.PlanTrabajo.Usuario.Usuario;
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
@Table(name = "Unidad")
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Unidad")
    private Long idUnidad;

    @Column(name = "NombreUnidad", nullable = false, length = 85)
    private String nombreUnidad;

    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    // -----RELACIONES----- //
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "RolId")
    @JsonIgnoreProperties("unidades")
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AreaId")
    @JsonIgnoreProperties("unidades")
    private Area area;

    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<Actividad> actividades = new ArrayList<>();

    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<Programa> programas = new ArrayList<>();

    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<Usuario> usuarios = new ArrayList<>();
}
