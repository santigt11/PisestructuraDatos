/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Santiago
 */
public class MatriculaAsignatura {
    private Integer id;
    private String curso;
    private Asignatura asignatura;

    public MatriculaAsignatura(Integer id, String curso, Asignatura asignatura) {
        this.id = id;
        this.curso = curso;
        this.asignatura = asignatura;
    }

    public MatriculaAsignatura() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}