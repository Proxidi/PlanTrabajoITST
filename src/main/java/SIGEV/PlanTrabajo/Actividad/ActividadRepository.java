package SIGEV.PlanTrabajo.Actividad;

import SIGEV.PlanTrabajo.dto.PlanTrabajoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepository extends CrudRepository<Actividad, Long> {
    @Query(
            value = """
        SELECT
            A.no_actividad            AS no,
            O.nombre_objetivo         AS objetivo,
            Co.nombre_componente      AS componente,
            U.nombre_unidad           AS unidadResponsable,
            (Usu.nombre + ' ' + Usu.ap_paterno + ' ' + Usu.ap_materno) AS jefeUnidad,
            A.nombre_actividad        AS actividad,
            A.medio_verificacion      AS medioVerificacion,
            A.indicador_resultado     AS indicadorResultado,
            UM.nombre_unidad_medida   AS unidadMedida,
            COUNT(DISTINCT M.id_mes)  AS cantidadAnual,

            MAX(CASE WHEN M.nombre_mes = 'Enero' THEN 1 ELSE 0 END)  AS cEnero,
            MAX(CASE WHEN M.nombre_mes = 'Febrero' THEN 1 ELSE 0 END) AS cFebrero,
            MAX(CASE WHEN M.nombre_mes = 'Marzo' THEN 1 ELSE 0 END)   AS cMarzo,
            MAX(CASE WHEN M.nombre_mes = 'Abril' THEN 1 ELSE 0 END)   AS cAbril,
            MAX(CASE WHEN M.nombre_mes = 'Mayo' THEN 1 ELSE 0 END)    AS cMayo,
            MAX(CASE WHEN M.nombre_mes = 'Junio' THEN 1 ELSE 0 END)   AS cJunio,
            MAX(CASE WHEN M.nombre_mes = 'Julio' THEN 1 ELSE 0 END)   AS cJulio,
            MAX(CASE WHEN M.nombre_mes = 'Agosto' THEN 1 ELSE 0 END)  AS cAgosto,
            MAX(CASE WHEN M.nombre_mes = 'Septiembre' THEN 1 ELSE 0 END) AS cSeptiembre,
            MAX(CASE WHEN M.nombre_mes = 'Octubre' THEN 1 ELSE 0 END)    AS cOctubre,
            MAX(CASE WHEN M.nombre_mes = 'Noviembre' THEN 1 ELSE 0 END)  AS cNoviembre,
            MAX(CASE WHEN M.nombre_mes = 'Diciembre' THEN 1 ELSE 0 END)  AS cDiciembre,

            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Enero' THEN Ca.presupuesto END), 0) AS pEnero,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Febrero' THEN Ca.presupuesto END), 0) AS pFebrero,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Marzo' THEN Ca.presupuesto END), 0)   AS pMarzo,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Abril' THEN Ca.presupuesto END), 0)   AS pAbril,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Mayo' THEN Ca.presupuesto END), 0)    AS pMayo,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Junio' THEN Ca.presupuesto END), 0)   AS pJunio,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Julio' THEN Ca.presupuesto END), 0)   AS pJulio,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Agosto' THEN Ca.presupuesto END), 0)  AS pAgosto,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Septiembre' THEN Ca.presupuesto END), 0) AS pSeptiembre,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Octubre' THEN Ca.presupuesto END), 0)    AS pOctubre,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Noviembre' THEN Ca.presupuesto END), 0)  AS pNoviembre,
            COALESCE(MAX(CASE WHEN M.nombre_mes = 'Diciembre' THEN Ca.presupuesto END), 0)  AS pDiciembre

        FROM actividad A
        INNER JOIN objetivo O         ON A.objetivo_id = O.id_objetivo
        INNER JOIN componente Co      ON A.componente_id = Co.id_componente
        INNER JOIN unidad U           ON A.unidad_id = U.id_unidad
        INNER JOIN usuario Usu        ON Usu.unidad_id = U.id_unidad
        INNER JOIN unidad_medida UM   ON A.unidad_medida_id = UM.id_unidad_medida
        LEFT  JOIN calendarizacion Ca ON Ca.actividad_id = A.id_actividad
        LEFT  JOIN mes M              ON Ca.mes_id = M.id_mes

        GROUP BY
            A.no_actividad,
            O.nombre_objetivo,
            Co.nombre_componente,
            U.nombre_unidad,
            Usu.nombre, Usu.ap_paterno, Usu.ap_materno,
            A.nombre_actividad,
            A.medio_verificacion,
            A.indicador_resultado,
            UM.nombre_unidad_medida

        ORDER BY A.no_actividad
        """,
            nativeQuery = true
    )
    List<PlanTrabajoProjection> fetchPlanTrabajo();
}
