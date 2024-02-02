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

    public Tutoria(Integer id, LocalDate fecha, String tema, Modalidad modalidad, Boolean impartida, Integer horario_ID) {
        this.id = id;
        this.fecha = fecha;
        this.tema = tema;
        this.modalidad = modalidad;
        this.impartida = impartida;
        this.horario_ID = horario_ID;
    }

    public Integer getHorario_ID() {
        return horario_ID;
    }

    public void setHorario_ID(Integer horario_ID) {
        this.horario_ID = horario_ID;
    }

    public Boolean getImpartida() {
        return impartida;
    }

    public void setImpartida(Boolean impartida) {
        this.impartida = impartida;
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

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "fecha":
                return this.fecha.compareTo(LocalDate.parse(valorBuscado));
            case "tema":
                return this.tema.compareToIgnoreCase(valorBuscado);
            case "modalidad":
                return this.modalidad.compareTo(Modalidad.valueOf(valorBuscado));
            case "idhorario":
                return this.horario_ID.compareTo(Integer.parseInt(valorBuscado));
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }

}
