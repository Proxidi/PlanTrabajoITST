package SIGEV.PlanTrabajo.Mes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesRepository extends CrudRepository<Mes, Long> {
}
