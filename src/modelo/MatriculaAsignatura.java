package modelo;

import controlador.Matriculas.MatriculaArchivos;

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

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "curso":
                return this.curso.compareToIgnoreCase(valorBuscado);
            case "idmatricula":
                return this.matricula_ID.compareTo(Integer.parseInt(valorBuscado));
            case "idasignatura":
                return this.asignatura_ID.compareTo(Integer.parseInt(valorBuscado));
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
