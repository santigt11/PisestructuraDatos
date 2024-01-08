package modelo;

import java.time.LocalDate;

public class Persona {

    //atributos
    private Integer id;
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String telefono;
    private Rol rol;

    //constructor vacio
    public Persona() {
    }

    //constructor que iniciliza los atributos
    public Persona(Integer id, String dni, String nombre, String apellido, LocalDate fechaNacimientoi, String telefono, Rol rol) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimientoi;
        this.telefono = telefono;
        this.rol = rol;
    }

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimientoi() {
        return fechaNacimiento;
    }

    public void setFechaNacimientoi(LocalDate fechaNacimientoi) {
        this.fechaNacimiento = fechaNacimientoi;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    //toString
    @Override
    public String toString() {
        return dni + " - " + apellido + " " + nombre;
    }

//metodo para comparar atributos de dos personas basado en un campo y tipo específico
    public Boolean compare(Persona p, String field, Integer type) {
        //0 menor 1 mayor
        switch (type) {
            case 0:// Si el tipo es 0 es menor
                if (field.equalsIgnoreCase("apellidos")) {
                    return apellido.compareTo(p.getApellido()) < 0;//compara
                } else if (field.equalsIgnoreCase("nombres")) {
                    return nombre.compareTo(p.getNombre()) < 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return dni.compareTo(p.getDni()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(p.getId()) < 0;
                }
            case 1: //si el tipo es 1 es mayor
                if (field.equalsIgnoreCase("apellidos")) {
                    return apellido.compareTo(p.getApellido()) > 0;//compara
                } else if (field.equalsIgnoreCase("nombres")) {
                    return nombre.compareTo(p.getNombre()) > 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return dni.compareTo(p.getDni()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(p.getId()) > 0;
                }
            default:
                throw new AssertionError();
        }
    }

    // Método para comparar un campo específico de la persona con un valor buscado
    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "nombre":
                return this.nombre.compareToIgnoreCase(valorBuscado);//Compara el nombre ignorando mayúsculas/minúsculas
            case "apellido":
                return this.apellido.compareToIgnoreCase(valorBuscado);
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "dni":
                return this.dni.compareToIgnoreCase(valorBuscado);
            default:
                //Lanza una excepción si el campo no es válido
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }

}
