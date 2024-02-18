package modelo;


public class AsignacionMatricula {

    private Integer id;
    private Boolean impartida;
    private Integer tutoria_ID;
    private Integer matriculaAsignatura_ID;

    public AsignacionMatricula() {
    }

    public AsignacionMatricula(Integer id, Boolean impartida, Integer tutoria_ID, Integer matriculaAsignatura_ID) {
        this.id = id;
        this.impartida = impartida;
        this.tutoria_ID = tutoria_ID;
        this.matriculaAsignatura_ID = matriculaAsignatura_ID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getImpartida() {
        return impartida;
    }

    public void setImpartida(Boolean impartida) {
        this.impartida = impartida;
    }

    public Integer getTutoria_ID() {
        return tutoria_ID;
    }

    public void setTutoria_ID(Integer tutoria_ID) {
        this.tutoria_ID = tutoria_ID;
    }

    public Integer getMatriculaAsignatura_ID() {
        return matriculaAsignatura_ID;
    }

    public void setMatriculaAsignatura_ID(Integer matriculaAsignatura_ID) {
        this.matriculaAsignatura_ID = matriculaAsignatura_ID;
    }

    

    @Override
    public String toString() {
        return tutoria_ID + "  -  " + matriculaAsignatura_ID;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "matricula_id":
//                System.out.println(this.matricula_ID.compareTo(Integer.parseInt(valorBuscado)));
                return this.tutoria_ID.compareTo(Integer.parseInt(valorBuscado));
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
