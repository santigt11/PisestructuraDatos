package modelo;

public class Carrera {
    private Integer id;
    private String nombre;
    private Integer numCiclos;
    private Integer idFacultad;

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

    public Integer getNumCiclos() {
        return numCiclos;
    }

    public void setNumCiclos(Integer numCiclos) {
        this.numCiclos = numCiclos;
    }

    public Integer getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    
}
