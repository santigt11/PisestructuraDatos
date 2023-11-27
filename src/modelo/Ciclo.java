/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author santi
 */
public class Ciclo {
    private Integer id;
    private Integer nroCiclo;

    public Ciclo() {
    }

    public Ciclo(Integer id, Integer nroCiclo) {
        this.id = id;
        this.nroCiclo = nroCiclo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNroCiclo() {
        return nroCiclo;
    }

    public void setNroCiclo(Integer nroCiclo) {
        this.nroCiclo = nroCiclo;
    }
}
