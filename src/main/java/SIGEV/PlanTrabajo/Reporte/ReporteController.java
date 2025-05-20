/*
package SIGEV.PlanTrabajo.Reporte;

import SIGEV.PlanTrabajo.Actividad.Actividad;
import SIGEV.PlanTrabajo.Actividad.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/reporte")
public class ReporteController {
    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    @GetMapping
    public ResponseEntity<Iterable<Reporte>> findAll() {
        return ResponseEntity.ok(reporteRepository.findAll());
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<Reporte> findById(@PathVariable Long idReporte) {
        Optional<Reporte> reporteOptional = reporteRepository.findById(idReporte);
        if (reporteOptional.isPresent()) {
            return ResponseEntity.ok(reporteOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Reporte> create(@RequestBody Reporte reporte, UriComponentsBuilder uriBuilder) {
        Optional<Actividad> actividadOptional = actividadRepository.findById(reporte.getActividad().getIdActividad());
        if (!actividadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        reporte.setActividad(actividadOptional.get());

        Reporte created = reporteRepository.save(reporte);
        URI uri = uriBuilder.path("reporte/{idReporte}").buildAndExpand(created.getIdReporte()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idReporte}")
    public ResponseEntity<Void> update(@PathVariable Long idReporte, @RequestBody Reporte reporte) {
        Optional<Actividad> actividadOptional = actividadRepository.findById(reporte.getActividad().getIdActividad());
        if (!actividadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Reporte reporteAnterior = reporteRepository.findById(idReporte).get();
        if (reporteAnterior != null) {
            reporte.setActividad(actividadOptional.get());
            reporteRepository.save(reporte);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idReporte}")
    public ResponseEntity<Void> delete(@PathVariable Long idReporte) {
        if (reporteRepository.findById(idReporte).isPresent()) {
            reporteRepository.deleteById(idReporte);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
 */