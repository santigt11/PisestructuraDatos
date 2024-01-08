package modelo;

public class MallaCurricular {

    private Integer id;
    private String descripcion;
    private String pensum;
    private boolean vigencia;
    private Integer carrera_ID;

    public MallaCurricular(Integer id, String descripcion, String pensum, Integer idCarrera) {
        this.id = id;
        this.descripcion = descripcion;
        this.pensum = pensum;
        this.carrera_ID = idCarrera;
    }

    public MallaCurricular() {
    }

    public MallaCurricular(Integer id, String descripcion, String pensum, Integer idCarrera, boolean vigencia) {
        this.id = id;
        this.descripcion = descripcion;
        this.pensum = pensum;
        this.vigencia = vigencia;
        this.carrera_ID = idCarrera;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPensum() {
        return pensum;
    }

    public void setPensum(String pensum) {
        this.pensum = pensum;
    }

    public Integer getIdCarrera() {
        return carrera_ID;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.carrera_ID = idCarrera;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    @Override
    public String toString() {
        return pensum + "  -  " + descripcion;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "descripcion":
                return this.descripcion.compareToIgnoreCase(valorBuscado);
            case "pensum":
                return this.pensum.compareToIgnoreCase(valorBuscado);
            case "idcarrera":
                return this.carrera_ID.compareTo(Integer.parseInt(valorBuscado));
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
