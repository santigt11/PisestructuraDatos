package modelo;

public class MallaCurricular {
    private Integer id;
    private String descripcion;
    private String pensum;
    private boolean vigente;

    public MallaCurricular(Integer id, String descripcion, String pensum, boolean vigente) {
        this.id = id;
        this.descripcion = descripcion;
        this.pensum = pensum;
        this.vigente = vigente;
    }

    public MallaCurricular() {
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

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
    
    
    
}
