package modelo;

import java.time.LocalDate;

public class Persona {

    private Integer id;
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimientoi;
    private String telefono;
    private Rol rol;

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
        return fechaNacimientoi;
    }

    public void setFechaNacimientoi(LocalDate fechaNacimientoi) {
        this.fechaNacimientoi = fechaNacimientoi;
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
    
    @Override
    public String toString() {
        return dni + " - " + apellido + " " + nombre;
    }
    
    public Boolean compare(Persona p, String field, Integer type) {
            //0 menor 1 mayor
            switch (type) {
                case 0:
                    if (field.equalsIgnoreCase("apellidos")) {
                        return apellido.compareTo(p.getApellido()) < 0;
                    } else if (field.equalsIgnoreCase("nombres")) {
                        return nombre.compareTo(p.getNombre()) < 0;
                    } else if (field.equalsIgnoreCase("dni")) {
                        return dni.compareTo(p.getDni()) < 0;
                    } else if (field.equalsIgnoreCase("id")) {
                        return id.compareTo(p.getId()) < 0;
                    }
                case 1:
                    if (field.equalsIgnoreCase("apellidos")) {
                        return apellido.compareTo(p.getApellido()) > 0;
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
}
