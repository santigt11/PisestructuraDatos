/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Santiago
 */
public class Tutoria {
    private Integer id;
    private LocalDate fecha;
    private String tema;
    private Modalidad modalidad;
    private Boolean impartida;
    private Integer horario_ID;

    public Tutoria(Integer id, LocalDate fecha, String tema, Modalidad modalidad, Boolean impartida, Integer idHorario) {
        this.id = id;
        this.fecha = fecha;
        this.tema = tema;
        this.modalidad = modalidad;
        this.impartida = impartida;
        this.horario_ID = idHorario;
    }
    
    public Tutoria() {
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

    public Integer getIdHorario() {
        return horario_ID;
    }

    public void setIdHorario(Integer idHorario) {
        this.horario_ID = idHorario;
    }
    
}