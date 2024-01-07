package modelo;

import java.time.LocalDate;

public class Contrato {
    private Integer id;
    private LocalDate fechaRegistro;
    private LocalDate fechaCulminacion;
    private Integer persona_DNI;
    private Integer asignatura_CODIGO;

    public Contrato() {
    }

    public Contrato(Integer id, LocalDate fechaRegistro, LocalDate fechaCulminacion, Integer idPersona, Integer idAsignatura) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.fechaCulminacion = fechaCulminacion;
        this.persona_DNI = idPersona;
        this.asignatura_CODIGO = idAsignatura;
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
        return persona_DNI;
    }

    public void setIdPersona(Integer idPersona) {
        this.persona_DNI = idPersona;
    }

    public Integer getCodigoAsignatura() {
        return asignatura_CODIGO;
    }

    public void setCodigoAsignatura(Integer idAsignatura) {
        this.asignatura_CODIGO = idAsignatura;
    }
}
