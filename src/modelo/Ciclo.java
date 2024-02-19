/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Santiago
 */
public class Ciclo {
    private Integer id;
    private Boolean estadiActivo;
    private String nombre;
    private Integer mallaCurricular_ID;

    public Ciclo(Integer id, Boolean estadiActivo, String nombre, Integer mallaCurricular_ID) {
        this.id = id;
        this.estadiActivo = estadiActivo;
        this.nombre = nombre;
        this.mallaCurricular_ID = mallaCurricular_ID;
    }

    public Ciclo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEstadiActivo() {
        return estadiActivo;
    }

    public void setEstadiActivo(Boolean estadiActivo) {
        this.estadiActivo = estadiActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMallaCurricular_ID() {
        return mallaCurricular_ID;
    }

    public void setMallaCurricular_ID(Integer mallaCurricular_ID) {
        this.mallaCurricular_ID = mallaCurricular_ID;
    }
}
