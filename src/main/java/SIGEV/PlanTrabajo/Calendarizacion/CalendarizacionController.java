package SIGEV.PlanTrabajo.Calendarizacion;

import SIGEV.PlanTrabajo.Actividad.Actividad;
import SIGEV.PlanTrabajo.Actividad.ActividadRepository;
import SIGEV.PlanTrabajo.Mes.Mes;
import SIGEV.PlanTrabajo.Mes.MesRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://plan-trabajo-itst.vercel.app")
@RestController
@RequestMapping("/calendarizacion")
public class CalendarizacionController {
    @Autowired
    private CalendarizacionRepository calendarizacionRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private MesRepository mesRepository;

    @GetMapping
    public ResponseEntity<List<Calendarizacion>> findAll() {
        List<Calendarizacion> calendarizaciones = (List<Calendarizacion>) calendarizacionRepository.findAll();
        calendarizaciones.forEach(c -> {
            Hibernate.initialize(c.getActividad());
            Hibernate.initialize(c.getMes());
        });
        return ResponseEntity.ok(calendarizaciones);
    }

    @GetMapping("/{idCalendarizacion}")
    public ResponseEntity<Calendarizacion> findById(@PathVariable Long idCalendarizacion) {
        Optional<Calendarizacion> calendarizacionOptional = calendarizacionRepository.findById(idCalendarizacion);
        if (calendarizacionOptional.isPresent()) {
            return ResponseEntity.ok(calendarizacionOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Calendarizacion> create(@RequestBody Calendarizacion calendarizacion, UriComponentsBuilder uriBuilder) {
        Optional<Mes> mesOptional = mesRepository.findById(calendarizacion.getMes().getIdMes());
        if (!mesOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        calendarizacion.setMes(mesOptional.get());

        Optional<Actividad> actividadOptional = actividadRepository.findById(calendarizacion.getActividad().getIdActividad());
        if (!actividadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        calendarizacion.setActividad(actividadOptional.get());

        Calendarizacion created = calendarizacionRepository.save(calendarizacion);
        URI uri = uriBuilder.path("calendarizacion/{idCalendarizacion}").buildAndExpand(created.getIdCalendarizacion()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idCalendarizacion}")
    public ResponseEntity<Void> update(@PathVariable Long idCalendarizacion, @RequestBody Calendarizacion calendarizacion) {
        Optional<Mes> mesOptional = mesRepository.findById(calendarizacion.getMes().getIdMes());
        if (!mesOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Actividad> actividadOptional = actividadRepository.findById(calendarizacion.getActividad().getIdActividad());
        if (!actividadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Calendarizacion calendarizacionAnterior = calendarizacionRepository.findById(idCalendarizacion).get();
        if (calendarizacionAnterior != null) {
            calendarizacion.setMes(mesOptional.get());
            calendarizacion.setActividad(actividadOptional.get());
            calendarizacionRepository.save(calendarizacion);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idCalendarizacion}")
    public ResponseEntity<Void> delete(@PathVariable Long idCalendarizacion) {
        if (calendarizacionRepository.findById(idCalendarizacion).isPresent()) {
            calendarizacionRepository.deleteById(idCalendarizacion);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}