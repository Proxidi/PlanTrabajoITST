package SIGEV.PlanTrabajo.Programa;

import SIGEV.PlanTrabajo.Unidad.Unidad;
import SIGEV.PlanTrabajo.Unidad.UnidadRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/programa")
public class ProgramaController {
    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private UnidadRepository unidadRepository;

    @GetMapping
    public ResponseEntity<List<Programa>> findAll() {
        List<Programa> programas = (List<Programa>) programaRepository.findAll();
        programas.forEach(u -> {
            Hibernate.initialize(u.getUnidad());
        });
        return ResponseEntity.ok(programas);
    }

    @GetMapping("/{idPrograma}")
    public ResponseEntity<Programa> findById(@PathVariable Long idPrograma) {
        Optional<Programa> programaOptional = programaRepository.findById(idPrograma);
        if (programaOptional.isPresent()) {
            return ResponseEntity.ok(programaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Programa> create(@RequestBody Programa programa, UriComponentsBuilder uriBuilder) {
        Optional<Unidad> unidadOptional = unidadRepository.findById(programa.getUnidad().getIdUnidad());
        if (!unidadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        programa.setUnidad(unidadOptional.get());

        Programa created = programaRepository.save(programa);
        URI uri = uriBuilder.path("programa/{idPrograma}").buildAndExpand(created.getIdPrograma()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idPrograma}")
    public ResponseEntity<Void> update(@PathVariable Long idPrograma, @RequestBody Programa programa) {
        Optional<Unidad> unidadOptional = unidadRepository.findById(programa.getUnidad().getIdUnidad());
        if (!unidadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Programa programaAnterior = programaRepository.findById(idPrograma).get();
        if (programaAnterior != null) {
            programa.setUnidad(unidadOptional.get());
            programa.setIdPrograma(programaAnterior.getIdPrograma());
            programaRepository.save(programa);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idPrograma}")
    public ResponseEntity<Void> delete(@PathVariable Long idPrograma) {
        if (programaRepository.findById(idPrograma).isPresent()) {
            programaRepository.deleteById(idPrograma);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
