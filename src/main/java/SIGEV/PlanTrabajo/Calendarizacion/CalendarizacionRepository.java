package SIGEV.PlanTrabajo.Calendarizacion;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarizacionRepository extends CrudRepository<Calendarizacion, Long> {
}
