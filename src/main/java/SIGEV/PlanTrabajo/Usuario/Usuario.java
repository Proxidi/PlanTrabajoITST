package SIGEV.PlanTrabajo.Usuario;

import SIGEV.PlanTrabajo.Unidad.Unidad;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @Column(name = "Id_Usuario")
    private String idUsuario;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Ap_paterno", nullable = false, length = 30)
    private String apPaterno;

    @Column(name = "Ap_materno", nullable = false, length = 30)
    private String apMaterno;

    @Column(name = "Contraseña", nullable = false, length = 50)
    private String contrasena;

    // -----RELACIONES----- //
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UnidadId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Unidad unidad;
}
