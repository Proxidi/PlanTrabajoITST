package SIGEV.PlanTrabajo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanTrabajoDTO {
    private int no;
    private String objetivo;
    private String componente;
    private String unidadResponsable;
    private String jefeUnidad;
    private String actividad;
    private String medioVerificacion;
    private String indicadorResultado;
    private String unidadMedida;
    private long cantidadAnual;

    // Calendarización
    @JsonProperty("cEnero")
    private Integer cEnero;
    @JsonProperty("cFebrero")
    private Integer cFebrero;
    @JsonProperty("cMarzo")
    private Integer cMarzo;
    @JsonProperty("cAbril")
    private Integer cAbril;
    @JsonProperty("cMayo")
    private Integer cMayo;
    @JsonProperty("cJunio")
    private Integer cJunio;
    @JsonProperty("cJulio")
    private Integer cJulio;
    @JsonProperty("cAgosto")
    private Integer cAgosto;
    @JsonProperty("cSeptiembre")
    private Integer cSeptiembre;
    @JsonProperty("cOctubre")
    private Integer cOctubre;
    @JsonProperty("cNoviembre")
    private Integer cNoviembre;
    @JsonProperty("cDiciembre")
    private Integer cDiciembre;

    // Presupuesto
    @JsonProperty("pEnero")
    private float pEnero;
    @JsonProperty("pFebrero")
    private float pFebrero;
    @JsonProperty("pMarzo")
    private float pMarzo;
    @JsonProperty("pAbril")
    private float pAbril;
    @JsonProperty("pMayo")
    private float pMayo;
    @JsonProperty("pJunio")
    private float pJunio;
    @JsonProperty("pJulio")
    private float pJulio;
    @JsonProperty("pAgosto")
    private float pAgosto;
    @JsonProperty("pSeptiembre")
    private float pSeptiembre;
    @JsonProperty("pOctubre")
    private float pOctubre;
    @JsonProperty("pNoviembre")
    private float pNoviembre;
    @JsonProperty("pDiciembre")
    private float pDiciembre;
}
