/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author steve
 */
public class Tutoria {
    
    private Integer id;
    private String tema;
    private String descripcion;
    private Modalidad modalidad;
    private EstadoTutoria estado;
    private Integer id_Horario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public EstadoTutoria getEstado() {
        return estado;
    }

    public void setEstado(EstadoTutoria estado) {
        this.estado = estado;
    }

    public Integer getId_Horario() {
        return id_Horario;
    }

    public void setId_Horario(Integer id_Horario) {
        this.id_Horario = id_Horario;
    }
    
    
    
}
