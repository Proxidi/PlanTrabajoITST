package SIGEV.PlanTrabajo.Componente;

import SIGEV.PlanTrabajo.Actividad.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = {"https://plan-trabajo-itst-git-main-proxidis-projects.vercel.app", "*"})
@RestController
@RequestMapping("/componente")
public class ComponenteController {
    @Autowired
    private ComponenteRepository componenteRepository;

    @GetMapping
    public ResponseEntity<Iterable<Componente>> findAll() {
        return ResponseEntity.ok(componenteRepository.findAll());
    }

    @GetMapping("/{idComponente}")
    public ResponseEntity<Componente> findById(@PathVariable Long idComponente) {
        Optional<Componente> componenteOptional = componenteRepository.findById(idComponente);
        if (componenteOptional.isPresent()) {
            return ResponseEntity.ok(componenteOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Componente> create(@RequestBody Componente componente, UriComponentsBuilder uriBuilder) {
        Componente created = componenteRepository.save(componente);
        URI uri = uriBuilder.path("componente/{idComponente}").buildAndExpand(created.getIdComponente()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idComponente}")
    public ResponseEntity<Void> update(@PathVariable Long idComponente, @RequestBody Componente componente) {
        Componente componenteAnterior = componenteRepository.findById(idComponente).get();
        if (componenteAnterior != null) {
            componente.setIdComponente(componenteAnterior.getIdComponente());
            componenteRepository.save(componente);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idComponente}")
    public ResponseEntity<Void> delete(@PathVariable Long idComponente) {
        if (componenteRepository.findById(idComponente).isPresent()) {
            componenteRepository.deleteById(idComponente);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
