package modelo;

import java.time.LocalDate;

public class Contrato {
    private Integer id;
    private LocalDate fechaRegistro;
    private LocalDate fechaCulminacion;
    private Integer idPersona;
    private Integer codigoAsignatura;

    public Contrato() {
    }

    public Contrato(Integer id, LocalDate fechaRegistro, LocalDate fechaCulminacion, Integer idPersona, Integer idAsignatura) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.fechaCulminacion = fechaCulminacion;
        this.idPersona = idPersona;
        this.codigoAsignatura = idAsignatura;
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

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(Integer idAsignatura) {
        this.codigoAsignatura = idAsignatura;
    }
}
