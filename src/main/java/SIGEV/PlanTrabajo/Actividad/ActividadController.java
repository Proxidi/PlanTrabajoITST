package SIGEV.PlanTrabajo.Actividad;
import SIGEV.PlanTrabajo.Programa.Programa;
import SIGEV.PlanTrabajo.Programa.ProgramaRepository;
import SIGEV.PlanTrabajo.Componente.Componente;
import SIGEV.PlanTrabajo.Componente.ComponenteRepository;
import SIGEV.PlanTrabajo.Objetivo.Objetivo;
import SIGEV.PlanTrabajo.Objetivo.ObjetivoRepository;
import SIGEV.PlanTrabajo.Unidad.Unidad;
import SIGEV.PlanTrabajo.Unidad.UnidadRepository;
import SIGEV.PlanTrabajo.UnidadMedida.UnidadMedidaRepository;
import SIGEV.PlanTrabajo.UnidadMedida.UnidadMedida;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"https://plan-trabajo-itst-git-main-proxidis-projects.vercel.app", "*"})
@RestController
@RequestMapping("/actividad")
public class ActividadController {
    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Autowired
    private ComponenteRepository componenteRepository;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @GetMapping
    public ResponseEntity<List<Actividad>> findAll() {
        List<Actividad> actividades = (List<Actividad>) actividadRepository.findAll();
        actividades.forEach(a -> {
            Hibernate.initialize(a.getPrograma());
            Hibernate.initialize(a.getObjetivo());
            Hibernate.initialize(a.getComponente());
            Hibernate.initialize(a.getUnidad());
            Hibernate.initialize(a.getUnidadMedida());
        });
        return ResponseEntity.ok(actividades);
    }

    @GetMapping("/{idActividad}")
    public ResponseEntity<Actividad> findById(@PathVariable Long idActividad) {
        Optional<Actividad> actividadOptional = actividadRepository.findById(idActividad);
        if (actividadOptional.isPresent()) {
            return ResponseEntity.ok(actividadOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Actividad> create(@RequestBody Actividad actividad, UriComponentsBuilder uriBuilder) {
        Optional<Programa> programaOptional = programaRepository.findById(actividad.getPrograma().getIdPrograma());
        if (!programaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        actividad.setPrograma(programaOptional.get());

        Optional<Objetivo> objetivoOptional = objetivoRepository.findById(actividad.getObjetivo().getIdObjetivo());
        if (!objetivoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        actividad.setObjetivo(objetivoOptional.get());

        Optional<Componente> componenteOptional = componenteRepository.findById(actividad.getComponente().getIdComponente());
        if (!componenteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        actividad.setComponente(componenteOptional.get());

        Optional<Unidad> unidadOptional = unidadRepository.findById(actividad.getUnidad().getIdUnidad());
        if (!unidadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        actividad.setUnidad(unidadOptional.get());

        Optional<UnidadMedida> unidadMedidaOptional = unidadMedidaRepository.findById(actividad.getUnidadMedida().getIdUnidadMedida());
        if (!unidadMedidaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        actividad.setUnidadMedida(unidadMedidaOptional.get());

        Actividad created = actividadRepository.save(actividad);
        URI uri = uriBuilder.path("actividad/{idActividad}").buildAndExpand(created.getIdActividad()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idActividad}")
    public ResponseEntity<Void> update(@PathVariable Long idActividad, @RequestBody Actividad actividad) {
        Optional<Programa> programaOptional = programaRepository.findById(actividad.getPrograma().getIdPrograma());
        if (!programaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Objetivo> objetivoOptional = objetivoRepository.findById(actividad.getObjetivo().getIdObjetivo());
        if (!objetivoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Componente> componenteOptional = componenteRepository.findById(actividad.getComponente().getIdComponente());
        if (!componenteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Unidad> unidadOptional = unidadRepository.findById(actividad.getUnidad().getIdUnidad());
        if (!unidadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<UnidadMedida> unidadMedidaOptional = unidadMedidaRepository.findById(actividad.getUnidadMedida().getIdUnidadMedida());
        if (!unidadMedidaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Actividad actividadAnterior = actividadRepository.findById(idActividad).get();
        if (actividadAnterior != null) {
            actividad.setPrograma(programaOptional.get());
            actividad.setObjetivo(objetivoOptional.get());
            actividad.setComponente(componenteOptional.get());
            actividad.setUnidad(unidadOptional.get());
            actividad.setUnidadMedida(unidadMedidaOptional.get());
            actividad.setIdActividad(actividadAnterior.getIdActividad());
            actividadRepository.save(actividad);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idActividad}")
    public ResponseEntity<Void> delete(@PathVariable Long idActividad) {
        if (actividadRepository.findById(idActividad).isPresent()) {
            actividadRepository.deleteById(idActividad);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
