package modelo;

public class Ciclo {

    private Integer id;
    private Boolean estadoActivo;
    private String nombre;
    private Integer mallaCurricular_ID;

    public Ciclo(Integer id, Boolean estadoActivo, String nombre, Integer mallaCurricular_ID) {
        this.id = id;
        this.estadoActivo = estadoActivo;
        this.nombre = nombre;
        this.mallaCurricular_ID = mallaCurricular_ID;
    }

    public Ciclo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(Boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMallaCurricular_ID() {
        return mallaCurricular_ID;
    }

    public void setMallaCurricular_ID(Integer mallaCurricular_ID) {
        this.mallaCurricular_ID = mallaCurricular_ID;
    }

    @Override
    public String toString() {
        return nombre + " [Estado: " + (estadoActivo ? "Activo]" : "Inactivo]");
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "nombre":
                return this.nombre.compareToIgnoreCase(valorBuscado);
            case "mallaCurricular_ID":
                return this.mallaCurricular_ID.compareTo(Integer.parseInt(valorBuscado));
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
