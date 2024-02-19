package modelo;

public class Asignatura {

    private Integer id;
    private String nombre;
    private String codigo;
    private Integer totalHoras;
    private Integer ciclo_ID;

    public Asignatura() {
    }

    public Asignatura(Integer id, String nombre, String codigo, Integer totalHoras, Integer idMalla) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.totalHoras = totalHoras;
        this.ciclo_ID = idMalla;
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
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
