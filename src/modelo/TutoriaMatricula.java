/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Santiago
 */
public class TutoriaMatricula {
    private Integer id;
    private boolean impartida;
    private Integer matriculaAsignatura_ID;
    private Integer tutoria_ID;

    public TutoriaMatricula() {
    }

    public TutoriaMatricula(Integer id, boolean impartida, Integer matriculaAsignatura_ID, Integer tutoria_ID) {
        this.id = id;
        this.impartida = impartida;
        this.matriculaAsignatura_ID = matriculaAsignatura_ID;
        this.tutoria_ID = tutoria_ID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMatricula() {
        return matriculaAsignatura_ID;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.matriculaAsignatura_ID = idMatricula;
    }

    public Integer getIdTutoria() {
        return tutoria_ID;
    }

    public void setIdTutoria(Integer idTutoria) {
        this.tutoria_ID = idTutoria;
    }
    
}
