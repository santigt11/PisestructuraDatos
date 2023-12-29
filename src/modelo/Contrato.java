package modelo;

import java.time.LocalDate;

public class Contrato {
    private Integer id;
    private LocalDate fechaRegistro;
    private LocalDate fechaCulminacion; 

    public Contrato() {
    }

    public Contrato(Integer id, LocalDate fechaRegistro, LocalDate fechaCulminacion) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.fechaCulminacion = fechaCulminacion;
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
}
