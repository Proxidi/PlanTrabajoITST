package SIGEV.PlanTrabajo.Objetivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = {"https://plan-trabajo-itst.vercel.app", "*"})
@RestController
@RequestMapping("/objetivo")
public class ObjetivoController {
    @Autowired
    private ObjetivoRepository objetivoRepository;

    @GetMapping
    public ResponseEntity<Iterable<Objetivo>> findAll() {
        return ResponseEntity.ok(objetivoRepository.findAll());
    }

    @GetMapping("/{idObjetivo}")
    public ResponseEntity<Objetivo> findById(@PathVariable Long idObjetivo) {
        Optional<Objetivo> objetivoOptional = objetivoRepository.findById(idObjetivo);
        if (objetivoOptional.isPresent()) {
            return ResponseEntity.ok(objetivoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Objetivo> create(@RequestBody Objetivo objetivoPIID, UriComponentsBuilder uriBuilder) {
        Objetivo created = objetivoRepository.save(objetivoPIID);
        URI uri = uriBuilder.path("objetivo-piid/{idObjetivo}").buildAndExpand(created.getIdObjetivo()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idObjetivo}")
    public ResponseEntity<Void> update(@PathVariable Long idObjetivo, @RequestBody Objetivo objetivoPIID) {
        Objetivo objetivoAnterior = objetivoRepository.findById(idObjetivo).get();
        if (objetivoAnterior != null) {
            objetivoPIID.setIdObjetivo(objetivoAnterior.getIdObjetivo());
            objetivoRepository.save(objetivoPIID);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idObjetivo}")
    public ResponseEntity<Void> delete(@PathVariable Long idObjetivo) {
        if (objetivoRepository.findById(idObjetivo).isPresent()) {
            objetivoRepository.deleteById(idObjetivo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
