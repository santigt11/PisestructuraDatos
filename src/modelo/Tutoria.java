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
    private Integer modalidad_ID;
    private String horaFin;
    private String comentario;
    private Integer asignacion_ID;
    private Boolean horarioValido;

    public Tutoria(Integer id, LocalDate fecha, String tema, String horaInicio, Integer modalidad_ID, String horaFin, String comentario, Integer asignacion_ID, Boolean horarioValido) {
        this.id = id;
        this.fecha = fecha;
        this.tema = tema;
        this.horaInicio = horaInicio;
        this.modalidad_ID = modalidad_ID;
        this.horaFin = horaFin;
        this.comentario = comentario;
        this.asignacion_ID = asignacion_ID;
        this.horarioValido = horarioValido;
    }
    
    public Tutoria() {
    }

    public Boolean getHorarioValido() {
        return horarioValido;
    }

    public void setHorarioValido(Boolean horarioValido) {
        this.horarioValido = horarioValido;
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
    
    public Boolean compare(Tutoria t, String field, Integer type) {
        //0 menor 1 mayor
        switch (type) {
            case 0:// Si el tipo es 0 es menor
                if (field.equalsIgnoreCase("tema")) {
                    return tema.compareTo(t.getTema()) < 0;//compara
                } else if (field.equalsIgnoreCase("asignacion_id")) {
                    return asignacion_ID.compareTo(t.getAsignacion_ID()) < 0;
                } else if (field.equalsIgnoreCase("fecha")) {
                    return fecha.compareTo(t.getFecha()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(t.getId()) < 0;
                }
            case 1: //si el tipo es 1 es mayor
                if (field.equalsIgnoreCase("tema")) {
                    return tema.compareTo(t.getTema()) > 0;//compara
                } else if (field.equalsIgnoreCase("asignacion_id")) {
                    return asignacion_ID.compareTo(t.getAsignacion_ID()) > 0;
                } else if (field.equalsIgnoreCase("fecha")) {
                    return fecha.compareTo(t.getFecha()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(t.getId()) > 0;
                }
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return fecha + " " + horaInicio + " " + horaFin;
    }
}
