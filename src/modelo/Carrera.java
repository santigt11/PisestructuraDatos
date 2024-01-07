package modelo;

public class Carrera {
    private Integer id;
    private String nombre;
    private Integer numCiclos;
    private Integer facultad_ID;

    public Carrera() {
    }

    public Carrera(Integer id, String nombre, Integer numCiclos, Integer idFacultad) {
        this.id = id;
        this.nombre = nombre;
        this.numCiclos = numCiclos;
        this.facultad_ID = idFacultad;
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

    public Integer getNumCiclos() {
        return numCiclos;
    }

    public void setNumCiclos(Integer numCiclos) {
        this.numCiclos = numCiclos;
    }

    public Integer getIdFacultad() {
        return facultad_ID;
    }

    public void setIdFacultad(Integer idFacultad) {
        this.facultad_ID = idFacultad;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    
}
