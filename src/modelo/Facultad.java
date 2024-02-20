package modelo;

public class Facultad {

    private Integer id;
    private String nombre;
    private String codigo;
    private String correo;
    private Integer universidad_ID;

    public Facultad() {
    }

    public Facultad(Integer id, String nombre, String codigo, String correo, Integer universidad_ID) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.correo = correo;
        this.universidad_ID = universidad_ID;
    }

    public Integer getUniversidad_ID() {
        return universidad_ID;
    }

    public void setUniversidad_ID(Integer universidad_ID) {
        this.universidad_ID = universidad_ID;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    @Override
    public String toString() {
        return nombre;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "nombre":
                return this.nombre.compareToIgnoreCase(valorBuscado);
            case "codigo":
                return this.codigo.compareToIgnoreCase(valorBuscado);
            case "correo":
                return this.correo.compareToIgnoreCase(valorBuscado);
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }

}
