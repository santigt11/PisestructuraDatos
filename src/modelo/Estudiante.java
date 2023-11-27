/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author santi
 */
public class Estudiante extends Persona{
    private Integer id;

    public Estudiante(Integer id, String cedula, String nombre, String apellido, Date fechaNacimiento, String direccion, String numeroTelefono) {
        super(cedula, nombre, apellido, fechaNacimiento, direccion, numeroTelefono);
        this.id = id;
    }

    public Estudiante() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
