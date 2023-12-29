package modelo;

import java.time.LocalDate;

public class Contrato {
    private Integer id;
    private LocalDate fechaRegistro;
    private LocalDate fechaCulminacion;
    private Persona docente;
    private Asignatura asignatura;

    public Contrato() {
    }

    public Contrato(Integer id, LocalDate fechaRegistro, LocalDate fechaCulminacion, Persona docente, Asignatura asignatura) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.fechaCulminacion = fechaCulminacion;
        this.docente = docente;
        this.asignatura = asignatura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaCulminacion() {
        return fechaCulminacion;
    }

    public void setFechaCulminacion(LocalDate fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }

    public Persona getDocente() {
        return docente;
    }

    public void setDocente(Persona docente) {
        this.docente = docente;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
    
    
}
