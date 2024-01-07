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
    private Integer matricula_ID;
    private Integer tutoria_ID;

    public TutoriaMatricula() {
    }

    public TutoriaMatricula(Integer id, Integer idMatricula, Integer idTutoria) {
        this.id = id;
        this.matricula_ID = idMatricula;
        this.tutoria_ID = idTutoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMatricula() {
        return matricula_ID;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.matricula_ID = idMatricula;
    }

    public Integer getIdTutoria() {
        return tutoria_ID;
    }

    public void setIdTutoria(Integer idTutoria) {
        this.tutoria_ID = idTutoria;
    }
    
}
