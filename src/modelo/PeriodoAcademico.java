/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Santiago
 */
public class PeriodoAcademico {
    private Integer id;
    private Date fechaIncio;
    private Date fechaFin;

    public PeriodoAcademico(Integer id, Date fechaIncio, Date fechaFin) {
        this.id = id;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
    }

    public PeriodoAcademico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(Date fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
