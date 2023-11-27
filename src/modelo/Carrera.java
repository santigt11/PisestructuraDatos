/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author santi
 */
public class Carrera {
    private Integer id;
    private Integer idPreiodo;

    public Carrera() {
    }

    public Carrera(Integer id, Integer idPreiodo) {
        this.id = id;
        this.idPreiodo = idPreiodo;
    }

    public Integer getIdPreiodo() {
        return idPreiodo;
    }

    public void setIdPreiodo(Integer idPreiodo) {
        this.idPreiodo = idPreiodo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
