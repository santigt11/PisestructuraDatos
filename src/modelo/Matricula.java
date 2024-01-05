/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.TDA.listas.DynamicList;
import java.util.Date;

/**
 *
 * @author Santiago
 */
public class Matricula {
    private Integer id;
    private Date fecha;
    private Boolean expActivo;
    private Integer idPersona;
    private Integer idPAcademico;

    public Matricula(Integer id, Date fecha, Boolean expActivo, Integer idPersona, Integer idPAcademico) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getExpActivo() {
        return expActivo;
    }

    public void setExpActivo(Boolean expActivo) {
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
}