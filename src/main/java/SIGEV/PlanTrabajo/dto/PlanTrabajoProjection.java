package SIGEV.PlanTrabajo.dto;

public interface PlanTrabajoProjection {
    int    getNo();
    String getObjetivo();
    String getComponente();
    String getUnidadResponsable();
    String getJefeUnidad();
    String getActividad();
    String getMedioVerificacion();
    String getIndicadorResultado();
    String getUnidadMedida();
    long   getCantidadAnual();

    Integer getCEnero();
    Integer getCFebrero();
    Integer getCMarzo();
    Integer getCAbril();
    Integer getCMayo();
    Integer getCJunio();
    Integer getCJulio();
    Integer getCAgosto();
    Integer getCSeptiembre();
    Integer getCOctubre();
    Integer getCNoviembre();
    Integer getCDiciembre();

    float getPEnero();
    float getPFebrero();
    float getPMarzo();
    float getPAbril();
    float getPMayo();
    float getPJunio();
    float getPJulio();
    float getPAgosto();
    float getPSeptiembre();
    float getPOctubre();
    float getPNoviembre();
    float getPDiciembre();
}
