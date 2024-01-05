package modelo;

public class MallaCurricular {
    private Integer id;
    private String descripcion;
    private String pensum;
    private Integer idCarrera;
    private boolean vigencia;

    public MallaCurricular() {
    }

    public MallaCurricular(Integer id, String descripcion, String pensum, Integer idCarrera, boolean vigencia) {
        this.id = id;
        this.descripcion = descripcion;
        this.pensum = pensum;
        this.idCarrera = idCarrera;
        this.vigencia = vigencia;
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
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
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
}
