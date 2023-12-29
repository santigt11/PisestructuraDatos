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
    private Boolean impartida;
    private Horario horario;

    public Tutoria(Integer id, Date fecha, String tema, Boolean impartida, Horario horario) {
        this.id = id;
        this.fecha = fecha;
        this.tema = tema;
        this.impartida = impartida;
        this.horario = horario;
    }

    public Tutoria() {
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
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
}