package modelo;

import controlador.TDA.listas.DynamicList;

public class MallaCurricular {
    private Integer id;
    private String descripcion;
    private String pensum;
    private Integer idCarrera;

    public MallaCurricular(Integer id, String descripcion, String pensum, Integer idCarrera) {
        this.id = id;
        this.descripcion = descripcion;
        this.pensum = pensum;
        this.idCarrera = idCarrera;
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

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }
    
    @Override
    public String toString() {
        return pensum + "  -  " + descripcion;
    }
}
