package modelo;

public class MatriculaAsignatura {

    private Integer id;
    private String curso;
    private Integer idMatricula;
    private Integer idAsignatura;

    public MatriculaAsignatura() {
    }

    public MatriculaAsignatura(Integer id, String curso, Integer idMatricula, Integer idAsignatura) {
        this.id = id;
        this.curso = curso;
        this.idMatricula = idMatricula;
        this.idAsignatura = idAsignatura;
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
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    @Override
    public String toString() {
        return curso + "  -  " + idAsignatura;
    }
}
