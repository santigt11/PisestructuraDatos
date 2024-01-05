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
public class Tutoria {
    private Integer id;
    private Date fecha;
    private String tema;
    private Modalidad modalidad;
    private Boolean impartida;
    private Integer idMatricula;
    private Integer idHorario;

    public Tutoria(Integer id, Date fecha, String tema, Modalidad modalidad, Boolean impartida, Integer idMatricula, Integer idHorario) {
        this.id = id;
        this.fecha = fecha;
        this.tema = tema;
        this.modalidad = modalidad;
        this.impartida = impartida;
        this.idMatricula = idMatricula;
        this.idHorario = idHorario;
    }
    
    public Tutoria() {
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

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Boolean getImpartida() {
        return impartida;
    }

    public void setImpartida(Boolean impartida) {
        this.impartida = impartida;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }
    
}