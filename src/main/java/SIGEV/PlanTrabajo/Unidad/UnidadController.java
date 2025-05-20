package SIGEV.PlanTrabajo.Unidad;

import SIGEV.PlanTrabajo.Area.Area;
import SIGEV.PlanTrabajo.Area.AreaRepository;
import SIGEV.PlanTrabajo.Rol.Rol;
import SIGEV.PlanTrabajo.Rol.RolRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = {"https://plan-trabajo-itst.vercel.app", "*"})
@RestController
@RequestMapping("/unidad")
public class UnidadController {
    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private AreaRepository areaRepository;

    @GetMapping
    public ResponseEntity<List<Unidad>> findAll() {
        List<Unidad> unidades = (List<Unidad>) unidadRepository.findAll();
        unidades.forEach(u -> {
            Hibernate.initialize(u.getRol());
            Hibernate.initialize(u.getArea());
        });
        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/{idUnidad}")
    public ResponseEntity<Unidad> findById(@PathVariable Long idUnidad) {
        Optional<Unidad> unidadOptional = unidadRepository.findById(idUnidad);
        if (unidadOptional.isPresent()) {
            return ResponseEntity.ok(unidadOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Unidad> create(@RequestBody Unidad unidad, UriComponentsBuilder uriBuilder) {
        Optional<Rol> rolOptional = rolRepository.findById(unidad.getRol().getIdRol());
        if (!rolOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        unidad.setRol(rolOptional.get());

        if (unidad.getArea() != null && unidad.getArea().getIdArea() != null) {
            Optional<Area> areaOptional = areaRepository.findById(unidad.getArea().getIdArea());
            if (!areaOptional.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            unidad.setArea(areaOptional.get());
        } else {
            unidad.setArea(null);
        }
        Unidad created = unidadRepository.save(unidad);
        URI uri = uriBuilder.path("unidad/{idUnidad}").buildAndExpand(created.getIdUnidad()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idUnidad}")
    public ResponseEntity<Void> update(@PathVariable Long idUnidad, @RequestBody Unidad unidadActualizada) {
        Optional<Unidad> unidadOptional = unidadRepository.findById(idUnidad);
        if (!unidadOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Validar Rol y Área
        Optional<Rol> rolOptional = rolRepository.findById(unidadActualizada.getRol().getIdRol());
        Optional<Area> areaOptional = areaRepository.findById(unidadActualizada.getArea().getIdArea());
        if (!rolOptional.isPresent() || !areaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Unidad unidadExistente = unidadOptional.get();
        unidadExistente.setNombreUnidad(unidadActualizada.getNombreUnidad());
        unidadExistente.setEmail(unidadActualizada.getEmail());
        unidadExistente.setRol(rolOptional.get());
        unidadExistente.setArea(areaOptional.get());

        unidadRepository.save(unidadExistente);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idUnidad}")
    public ResponseEntity<Void> delete(@PathVariable Long idUnidad) {
        if (unidadRepository.findById(idUnidad).isPresent()) {
            unidadRepository.deleteById(idUnidad);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
