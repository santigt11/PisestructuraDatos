package modelo;


public class AsignacionMatricula {

    private Integer id;
    private String curso;
    private Integer matricula_ID;
    private String asignatura_Codigo;

    public AsignacionMatricula() {
    }

    public AsignacionMatricula(Integer id, String curso, Integer idMatricula, String idAsignatura) {
        this.id = id;
        this.curso = curso;
        this.matricula_ID = idMatricula;
        this.asignatura_Codigo = idAsignatura;
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

    public Integer getMatricula_ID() {
        return matricula_ID;
    }

    public void setMatricula_ID(Integer idMatricula) {
        this.matricula_ID = idMatricula;
    }

    public String getAsignatura_Codigo() {
        return asignatura_Codigo;
    }

    public void setAsignatura_Codigo(String idAsignatura) {
        this.asignatura_Codigo = idAsignatura;
    }

    @Override
    public String toString() {
        return curso + "  -  " + asignatura_Codigo;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "curso":
                return this.curso.compareToIgnoreCase(valorBuscado);
            case "matricula_id":
//                System.out.println(this.matricula_ID.compareTo(Integer.parseInt(valorBuscado)));
                return this.matricula_ID.compareTo(Integer.parseInt(valorBuscado));
            case "asignatura_codigo":
                return this.asignatura_Codigo.compareTo(valorBuscado);
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
