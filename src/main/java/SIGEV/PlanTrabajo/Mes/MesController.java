package SIGEV.PlanTrabajo.Mes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/mes")
public class MesController {
    @Autowired
    private MesRepository mesRepository;

    @GetMapping
    public ResponseEntity<Iterable<Mes>> findAll() {
        return ResponseEntity.ok(mesRepository.findAll());
    }

    @GetMapping("/{idMes}")
    public ResponseEntity<Mes> findById(@PathVariable Long idMes) {
        Optional<Mes> mesOptional = mesRepository.findById(idMes);
        if (mesOptional.isPresent()) {
            return ResponseEntity.ok(mesOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Mes> create(@RequestBody Mes mes, UriComponentsBuilder uriBuilder) {
        Mes created = mesRepository.save(mes);
        URI uri = uriBuilder.path("mes/{idMes}").buildAndExpand(created.getIdMes()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idMes}")
    public ResponseEntity<Void> update(@PathVariable Long idMes, @RequestBody Mes mes) {
        Mes mesAnterior = mesRepository.findById(idMes).get();
        if (mesAnterior != null) {
            mes.setIdMes(mesAnterior.getIdMes());
            mesRepository.save(mes);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idMes}")
    public ResponseEntity<Void> delete(@PathVariable Long idMes) {
        if (mesRepository.findById(idMes).isPresent()) {
            mesRepository.deleteById(idMes);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
