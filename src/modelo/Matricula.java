
package modelo;

import java.time.LocalDate;

public class Matricula {
    private Integer id;
    private LocalDate fecha;
    private boolean expActivo;
    private Integer idPersona;
    private Integer idPAcademico;

    public Matricula(Integer id, LocalDate fecha, boolean expActivo, Integer idPersona, Integer idPAcademico) {
        this.id = id;
        this.fecha = fecha;
        this.expActivo = expActivo;
        this.idPersona = idPersona;
        this.idPAcademico = idPAcademico;
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
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPAcademico() {
        return idPAcademico;
    }

    public void setIdPAcademico(Integer idPAcademico) {
        this.idPAcademico = idPAcademico;
    }
    
    @Override
    public String toString() {
        return id + " - " + fecha;
    }
}