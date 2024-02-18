package modelo;

public class Asignacion {

    private Integer id;
    private String asignatura_CODIGO;
    private String persona_DNI;

    public Asignacion() {
    }

    public Asignacion(Integer id, String asignatura_CODIGO, String persona_DNI) {
        this.id = id;
        this.asignatura_CODIGO = asignatura_CODIGO;
        this.persona_DNI = persona_DNI;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getDniPersona() {
        return persona_DNI;
    }

    public void setDniPersona(String persona_DNI) {
        this.persona_DNI = persona_DNI;
    }

    public String getPersona_DNI() {
        return persona_DNI;
    }

    public void setPersona_DNI(String persona_DNI) {
        this.persona_DNI = persona_DNI;
    }

    public String getAsignatura_CODIGO() {
        return asignatura_CODIGO;
    }

    public void setAsignatura_CODIGO(String asignatura_CODIGO) {
        this.asignatura_CODIGO = asignatura_CODIGO;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "dnidocente":
                return this.persona_DNI.compareToIgnoreCase(valorBuscado);
            case "codasignatura":
                return this.asignatura_CODIGO.compareToIgnoreCase(valorBuscado);
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
