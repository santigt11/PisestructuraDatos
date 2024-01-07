
package modelo;

import java.time.LocalDate;

public class Matricula {
    private Integer id;
    private LocalDate fecha;
    private boolean expActivo;
    private Integer persona_DNI;
    private Integer periodoAcademico_ID;

    public Matricula(Integer id, LocalDate fecha, boolean expActivo, Integer idPersona, Integer idPAcademico) {
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

    public Integer getIdPersona() {
        return persona_DNI;
    }

    public void setIdPersona(Integer idPersona) {
        this.persona_DNI = idPersona;
    }

    public Integer getIdPAcademico() {
        return periodoAcademico_ID;
    }

    public void setIdPAcademico(Integer idPAcademico) {
        this.periodoAcademico_ID = idPAcademico;
    }
    
    @Override
    public String toString() {
        return id + " - " + fecha;
    }
}