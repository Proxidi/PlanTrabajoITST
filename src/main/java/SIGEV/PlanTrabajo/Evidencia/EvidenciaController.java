/*
package SIGEV.PlanTrabajo.Evidencia;

import SIGEV.PlanTrabajo.Actividad.Actividad;
import SIGEV.PlanTrabajo.Actividad.ActividadRepository;
import SIGEV.PlanTrabajo.Reporte.Reporte;
import SIGEV.PlanTrabajo.Reporte.ReporteRepository;
import SIGEV.PlanTrabajo.Unidad.Unidad;
import SIGEV.PlanTrabajo.Unidad.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/evidencia")
public class EvidenciaController {
    @Autowired
    private EvidenciaRepository evidenciaRepository;

    @Autowired
    private UnidadRepository unidadRepository;
    @Autowired
    private ActividadRepository actividadRepository;
    @Autowired
    private ReporteRepository reporteRepository;

    @GetMapping
    public ResponseEntity<Iterable<Evidencia>> findAll() {
        return ResponseEntity.ok(evidenciaRepository.findAll());
    }

    @GetMapping("/{idEvidencia}")
    public ResponseEntity<Evidencia> findById(@PathVariable Long idEvidencia) {
        Optional<Evidencia> evidenciaOptional = evidenciaRepository.findById(idEvidencia);
        if (evidenciaOptional.isPresent()) {
            return ResponseEntity.ok(evidenciaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Evidencia> create(@RequestBody Evidencia evidencia, UriComponentsBuilder uriBuilder) {
        Optional<Unidad> unidadOptional = unidadRepository.findById(evidencia.getUnidad().getIdUnidad());
        if (!unidadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        evidencia.setUnidad(unidadOptional.get());

        Optional<Actividad> actividadOptional = actividadRepository.findById(evidencia.getActividad().getIdActividad());
        if (!actividadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        evidencia.setActividad(actividadOptional.get());

        Optional<Reporte> reporteOptional = reporteRepository.findById(evidencia.getReporte().getIdReporte());
        if (!reporteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        evidencia.setReporte(reporteOptional.get());

        Evidencia created = evidenciaRepository.save(evidencia);
        URI uri = uriBuilder.path("evidencia/{idEvidencia}").buildAndExpand(created.getIdEvidencia()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idEvidencia}")
    public ResponseEntity<Void> update(@PathVariable Long idEvidencia, @RequestBody Evidencia evidencia) {
        Optional<Unidad> unidadOptional = unidadRepository.findById(evidencia.getUnidad().getIdUnidad());
        if (!unidadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Actividad> actividadOptional = actividadRepository.findById(evidencia.getActividad().getIdActividad());
        if (!actividadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Reporte> reporteOptional = reporteRepository.findById(evidencia.getReporte().getIdReporte());
        if (!reporteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Evidencia evidenciaAnterior = evidenciaRepository.findById(idEvidencia).get();
        if (evidenciaAnterior != null) {
            evidencia.setUnidad(unidadOptional.get());
            evidencia.setActividad(actividadOptional.get());
            evidencia.setReporte(reporteOptional.get());
            evidencia.setIdEvidencia(evidenciaAnterior.getIdEvidencia());
            evidenciaRepository.save(evidencia);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idEvidencia}")
    public ResponseEntity<Void> delete(@PathVariable Long idEvidencia) {
        if (evidenciaRepository.findById(idEvidencia).isPresent()) {
            evidenciaRepository.deleteById(idEvidencia);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
 */