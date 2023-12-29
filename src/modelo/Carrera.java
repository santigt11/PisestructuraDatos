package modelo;

public class Carrera {
    private Integer id;
    private String nombre;
    private Integer numCiclos;

    public Carrera() {
    }

    public Carrera(Integer id, String nombre, Integer numCiclos) {
        this.id = id;
        this.nombre = nombre;
        this.numCiclos = numCiclos;
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
    
    
}
