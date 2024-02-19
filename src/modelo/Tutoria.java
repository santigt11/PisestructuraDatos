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
    private String horaInicio;
    private String horaFin;
    private String comentario;
    private Integer modalidad_ID;
    private Integer asignacion_ID;
    
    public Tutoria() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getAsignacion_ID() {
        return asignacion_ID;
    }

    public void setAsignacion_ID(Integer asignacion_ID) {
        this.asignacion_ID = asignacion_ID;
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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getModalidad_ID() {
        return modalidad_ID;
    }

    public void setModalidad_ID(Integer modalidad_ID) {
        this.modalidad_ID = modalidad_ID;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "fecha":
                return this.fecha.compareTo(LocalDate.parse(valorBuscado));
            case "tema":
                return this.tema.compareToIgnoreCase(valorBuscado);
            case "asignacion_id":
                return this.asignacion_ID.compareTo(Integer.parseInt(valorBuscado));
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }

}
