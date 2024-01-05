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
    private Integer idMatricula;
    private Integer idTutoria;

    public TutoriaMatricula() {
    }

    public TutoriaMatricula(Integer id, Integer idMatricula, Integer idTutoria) {
        this.id = id;
        this.idMatricula = idMatricula;
        this.idTutoria = idTutoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Integer getIdTutoria() {
        return idTutoria;
    }

    public void setIdTutoria(Integer idTutoria) {
        this.idTutoria = idTutoria;
    }
    
}
