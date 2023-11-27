/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import enumeracion.EstadoMatricula;

/**
 *
 * @author santi
 */
public class Matricula {
    private Integer id;
    private EstadoMatricula estado;

    public Matricula(Integer id, EstadoMatricula estado) {
        this.id = id;
        this.estado = estado;
    }

    public Matricula() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoMatricula getEstado() {
        return estado;
    }

    public void setEstado(EstadoMatricula estado) {
        this.estado = estado;
    }
}
