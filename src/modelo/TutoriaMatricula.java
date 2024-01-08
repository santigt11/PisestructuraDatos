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
    
    public TutoriaMatricula(Integer id, Integer matriculaAsignatura_ID, Integer tutoria_ID, boolean impartida) {
        this.id = id;
        this.matriculaAsignatura_ID = matriculaAsignatura_ID;
        this.tutoria_ID = tutoria_ID;
        this.impartida = impartida;
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

    public Integer getMatriculaAsignatura_ID() {
        return matriculaAsignatura_ID;
    }

    public void setMatriculaAsignatura_ID(Integer matriculaAsignatura_ID) {
        this.matriculaAsignatura_ID = matriculaAsignatura_ID;
    }

    public Integer getTutoria_ID() {
        return tutoria_ID;
    }

    public void setTutoria_ID(Integer tutoria_ID) {
        this.tutoria_ID = tutoria_ID;
    }

    public boolean isImpartida() {
        return impartida;
    }

    public void setImpartida(boolean impartida) {
        this.impartida = impartida;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "idmatricula":
                return this.matriculaAsignatura_ID.compareTo(Integer.parseInt(valorBuscado));
            case "idtutoria":
                return this.tutoria_ID.compareTo(Integer.parseInt(valorBuscado)); 
            case "impartida":
                return this.impartida == Boolean.parseBoolean(valorBuscado) ? 0 : 1;
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }

}
