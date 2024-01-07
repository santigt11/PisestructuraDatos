package modelo;

import controlador.Academico.MatriculaArchivos;

public class MatriculaAsignatura {

    private Integer id;
    private String curso;
    private Integer matricula_ID;
    private Integer asignatura_ID;

    public MatriculaAsignatura() {
    }

    public MatriculaAsignatura(Integer id, String curso, Integer idMatricula, Integer idAsignatura) {
        this.id = id;
        this.curso = curso;
        this.matricula_ID = idMatricula;
        this.asignatura_ID = idAsignatura;
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

    public Integer getIdMatricula() {
        return matricula_ID;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.matricula_ID = idMatricula;
    }

    public Integer getIdAsignatura() {
        return asignatura_ID;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.asignatura_ID = idAsignatura;
    }

    @Override
    public String toString() {
        return curso + "  -  " + asignatura_ID;
    }
}
