package SIGEV.PlanTrabajo.PlanTrabajo;

import SIGEV.PlanTrabajo.dto.PlanTrabajoDTO;
import SIGEV.PlanTrabajo.Actividad.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plan-trabajo")
@CrossOrigin(origins = "http://localhost:5173")
public class PlanTrabajoController {

    @Autowired
    private ActividadRepository actividadRepository;

    @GetMapping
    public List<PlanTrabajoDTO> obtenerPlanTrabajo() {
        return actividadRepository.fetchPlanTrabajo().stream()
                .map(p -> new PlanTrabajoDTO(
                        p.getNo(), p.getObjetivo(), p.getComponente(), p.getUnidadResponsable(),
                        p.getJefeUnidad(), p.getActividad(), p.getMedioVerificacion(),
                        p.getIndicadorResultado(), p.getUnidadMedida(), p.getCantidadAnual(),

                        p.getCEnero(), p.getCFebrero(), p.getCMarzo(), p.getCAbril(),
                        p.getCMayo(), p.getCJunio(), p.getCJulio(), p.getCAgosto(),
                        p.getCSeptiembre(), p.getCOctubre(), p.getCNoviembre(), p.getCDiciembre(),

                        p.getPEnero(), p.getPFebrero(), p.getPMarzo(), p.getPAbril(),
                        p.getPMayo(), p.getPJunio(), p.getPJulio(), p.getPAgosto(),
                        p.getPSeptiembre(), p.getPOctubre(), p.getPNoviembre(), p.getPDiciembre()
                ))
                .collect(Collectors.toList());
    }
}