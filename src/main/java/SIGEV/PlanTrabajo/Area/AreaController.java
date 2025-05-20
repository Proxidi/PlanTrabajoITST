package SIGEV.PlanTrabajo.Area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "https://plan-trabajo-itst.vercel.app")
@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaRepository areaRepository;

    @GetMapping
    public ResponseEntity<Iterable<Area>> findAll() {
        return ResponseEntity.ok(areaRepository.findAll());
    }

    @GetMapping("/{idArea}")
    public ResponseEntity<Area> findById(@PathVariable Long idArea) {
        Optional<Area> areaOptional = areaRepository.findById(idArea);
        if (areaOptional.isPresent()) {
            return ResponseEntity.ok(areaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Area> create(@RequestBody Area area, UriComponentsBuilder uriBuilder) {
        Area created = areaRepository.save(area);
        URI uri = uriBuilder.path("area/{idArea}").buildAndExpand(created.getIdArea()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idArea}")
    public ResponseEntity<Void> update(@PathVariable Long idArea, @RequestBody Area area) {
        Area areaAnterior = areaRepository.findById(idArea).get();
        if (areaAnterior != null) {
            area.setIdArea(areaAnterior.getIdArea());
            areaRepository.save(area);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idArea}")
    public ResponseEntity<Void> delete(@PathVariable Long idArea) {
        if (areaRepository.findById(idArea).isPresent()) {
            areaRepository.deleteById(idArea);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
