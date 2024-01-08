package modelo;

import java.time.LocalDate;

public class Matricula {

    private Integer id;
    private LocalDate fecha;
    private Boolean expActivo;
    private String persona_DNI;
    private Integer periodoAcademico_ID;

    public Matricula(Integer id, LocalDate fecha, Boolean expActivo, String idPersona, Integer idPAcademico) {
        this.id = id;
        this.fecha = fecha;
        this.expActivo = expActivo;
        this.persona_DNI = idPersona;
        this.periodoAcademico_ID = idPAcademico;
    }

    public Matricula() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isExpActivo() {
        return expActivo;
    }

    public void setExpActivo(boolean expActivo) {
        this.expActivo = expActivo;
    }

    public String getPersona_DNI() {
        return persona_DNI;
    }

    public void setPersona_DNI(String idPersona) {
        this.persona_DNI = idPersona;
    }

    public Integer getPeriodoAcademico_ID() {
        return periodoAcademico_ID;
    }

    public void setPeriodoAcademico_ID(Integer idPAcademico) {
        this.periodoAcademico_ID = idPAcademico;
    }

    @Override
    public String toString() {
        return id + " - " + fecha;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "fecha":
                return this.fecha.compareTo(LocalDate.parse(valorBuscado));
            case "persona_dni":
                return this.persona_DNI.compareTo(valorBuscado);
            case "periodoacademico_id":
                return this.periodoAcademico_ID.compareTo(Integer.parseInt(valorBuscado));
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
