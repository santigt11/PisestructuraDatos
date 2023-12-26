package modelo;

public class Asignatura {
    private Integer id;
    private String nombre;
    private String codigo;
    private Integer totalHoras;

    public Asignatura(Integer id, String nombre, String codigo, Integer totalHoras) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.totalHoras = totalHoras;
    }

    public Asignatura() {
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
    
    
}
