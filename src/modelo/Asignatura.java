package modelo;

public class Asignatura {

    private Integer id;
    private String nombre;
    private String codigo;
    private Integer totalHoras;
    private Integer ciclo_ID;
    private Integer tipoUnidad_ID;
    
    public Asignatura() {
    }

    public Asignatura(Integer id, String nombre, String codigo, Integer totalHoras, Integer ciclo_ID, Integer tipoUnidad_ID) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.totalHoras = totalHoras;
        this.ciclo_ID = ciclo_ID;
        this.tipoUnidad_ID = tipoUnidad_ID;
    }
    
    public Integer getTipoUnidad_ID() {
        return tipoUnidad_ID;
    }

    public void setTipoUnidad_ID(Integer tipoUnidad_ID) {
        this.tipoUnidad_ID = tipoUnidad_ID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(Integer totalHoras) {
        this.totalHoras = totalHoras;
    }

    public Integer getCiclo_ID() {
        return ciclo_ID;
    }

    public void setCiclo_ID(Integer ciclo_ID) {
        this.ciclo_ID = ciclo_ID;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "codigo":
                return this.codigo.compareToIgnoreCase(valorBuscado);
            case "nombre":
                return this.nombre.compareToIgnoreCase(valorBuscado);
            case "mallaCurricular_ID":
                return this.ciclo_ID.compareTo(Integer.parseInt(valorBuscado));
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
    
    public Boolean compare(Asignatura a, String field, Integer type) {
        //0 menor 1 mayor
        switch (type) {
            case 0:// Si el tipo es 0 es menor
                if (field.equalsIgnoreCase("ciclo_id")) {
                    return ciclo_ID.compareTo(a.getCiclo_ID()) < 0;//compara
                } else if (field.equalsIgnoreCase("nombre")) {
                    return nombre.compareTo(a.getNombre()) < 0;
                } else if (field.equalsIgnoreCase("codigo")) {
                    return codigo.compareTo(a.getCodigo()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(a.getId()) < 0;
                }else if (field.equalsIgnoreCase("tipoUnidad_id")) {
                    return tipoUnidad_ID.compareTo(a.getTipoUnidad_ID()) < 0;
                }
            case 1: //si el tipo es 1 es mayor
                if (field.equalsIgnoreCase("ciclo_id")) {
                    return ciclo_ID.compareTo(a.getCiclo_ID()) > 0;//compara
                } else if (field.equalsIgnoreCase("nombre")) {
                    return nombre.compareTo(a.getNombre()) > 0;
                } else if (field.equalsIgnoreCase("codigo")) {
                    return codigo.compareTo(a.getCodigo()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(a.getId()) > 0;
                }else if (field.equalsIgnoreCase("tipoUnidad_id")) {
                    return tipoUnidad_ID.compareTo(a.getTipoUnidad_ID()) > 0;
                }
            default:
                throw new AssertionError();
        }
    }
}
