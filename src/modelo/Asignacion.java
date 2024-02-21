package modelo;

public class Asignacion {

    private Integer id;
    private String asignatura_CODIGO;
    private Integer usuario_ID;
    private Integer periodoAcademico_ID;

    public Asignacion() {
    }

    public Asignacion(Integer id, String asignatura_CODIGO, Integer usuario_ID, Integer periodoAcademico_ID) {
        this.id = id;
        this.asignatura_CODIGO = asignatura_CODIGO;
        this.usuario_ID = usuario_ID;
        this.periodoAcademico_ID = periodoAcademico_ID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsignatura_CODIGO() {
        return asignatura_CODIGO;
    }

    public void setAsignatura_CODIGO(String asignatura_CODIGO) {
        this.asignatura_CODIGO = asignatura_CODIGO;
    }

    public Integer getUsuario_ID() {
        return usuario_ID;
    }

    public void setUsuario_ID(Integer usuario_ID) {
        this.usuario_ID = usuario_ID;
    }

    public Integer getPeriodoAcademico_ID() {
        return periodoAcademico_ID;
    }

    public void setPeriodoAcademico_ID(Integer periodoAcademico_ID) {
        this.periodoAcademico_ID = periodoAcademico_ID;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "usuario_id":
                return this.usuario_ID.compareTo(Integer.parseInt(valorBuscado));
            case "asignatura_codigo":
                return this.asignatura_CODIGO.compareToIgnoreCase(valorBuscado);
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
    
    public Boolean compare(Asignacion a, String field, Integer type) {
        //0 menor 1 mayor
        switch (type) {
            case 0:// Si el tipo es 0 es menor
                if (field.equalsIgnoreCase("periodoAcademico_id")) {
                    return periodoAcademico_ID.compareTo(a.getPeriodoAcademico_ID()) < 0;//compara
                } else if (field.equalsIgnoreCase("asignatura_codigo")) {
                    return asignatura_CODIGO.compareTo(a.getAsignatura_CODIGO()) < 0;
                } else if (field.equalsIgnoreCase("usuario_id")) {
                    return usuario_ID.compareTo(a.getUsuario_ID()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(a.getId()) < 0;
                }
            case 1: //si el tipo es 1 es mayor
                if (field.equalsIgnoreCase("periodoAcademico_id")) {
                    return periodoAcademico_ID.compareTo(a.getPeriodoAcademico_ID()) > 0;//compara
                } else if (field.equalsIgnoreCase("asignatura_codigo")) {
                    return asignatura_CODIGO.compareTo(a.getAsignatura_CODIGO()) > 0;
                } else if (field.equalsIgnoreCase("usuario_id")) {
                    return usuario_ID.compareTo(a.getUsuario_ID()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(a.getId()) > 0;
                }
            default:
                throw new AssertionError();
        }
    }
}
