package SIGEV.PlanTrabajo.UnidadMedida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = {"https://plan-trabajo-itst-git-main-proxidis-projects.vercel.app", "*"})
@RestController
@RequestMapping("/unidad-medida")
public class UnidadMedidaController {
    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @GetMapping
    public ResponseEntity<Iterable<UnidadMedida>> findAll() {
        return ResponseEntity.ok(unidadMedidaRepository.findAll());
    }

    @GetMapping("/{idUnidadMedida}")
    public ResponseEntity<UnidadMedida> findById(@PathVariable Long idUnidadMedida) {
        Optional<UnidadMedida> unidadOptional = unidadMedidaRepository.findById(idUnidadMedida);
        if (unidadOptional.isPresent()) {
            return ResponseEntity.ok(unidadOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UnidadMedida> create(@RequestBody UnidadMedida unidadMedida, UriComponentsBuilder uriBuilder) {
        UnidadMedida created = unidadMedidaRepository.save(unidadMedida);
        URI uri = uriBuilder.path("unidad-medida/{idUnidadMedida}").buildAndExpand(created.getIdUnidadMedida()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idUnidadMedida}")
    public ResponseEntity<Void> update(@PathVariable Long idUnidadMedida, @RequestBody UnidadMedida unidadMedida) {
        UnidadMedida unidadAnterior = unidadMedidaRepository.findById(idUnidadMedida).get();
        if (unidadAnterior != null) {
            unidadMedida.setIdUnidadMedida(unidadAnterior.getIdUnidadMedida());
            unidadMedidaRepository.save(unidadMedida);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idUnidadMedida}")
    public ResponseEntity<Void> delete(@PathVariable Long idUnidadMedida) {
        if (unidadMedidaRepository.findById(idUnidadMedida).isPresent()) {
            unidadMedidaRepository.deleteById(idUnidadMedida);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
