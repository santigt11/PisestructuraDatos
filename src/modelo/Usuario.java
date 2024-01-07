/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Santiago
 */
public class Usuario {

    private Integer id;
    private String correo;
    private String clave;
    private Integer id_Persona;

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }
    public Integer getId_Persona() {
        return id_Persona;
    }

    public void setId_Persona(Integer id_Persona) {
        this.id_Persona = id_Persona;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    


/*public Boolean compare(Usuario u, String field, Integer type) {
        switch (type) {
            case 0 -> {
                if (field.equalsIgnoreCase("correo")) {
                    return this.getCorreo().toLowerCase().compareTo(u.getCorreo().toLowerCase()) < 0;
                }
                else if (field.equalsIgnoreCase("rol")) {
                    return this.rol.getName().compareTo(u.getRol().getName()) > 0;
                }
            }
            case 1 -> {
                if (field.equalsIgnoreCase("correo")) {
                    return this.getCorreo().toLowerCase().compareTo(u.getCorreo().toLowerCase()) < 0;
                }
                else if (field.equalsIgnoreCase("rol")) {
                    return this.rol.getName().compareTo(u.getRol().getName()) < 0;
                }
            }
            default -> {
                return false;
            }
        }
        return null;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "correo":
                return this.getCorreo().compareToIgnoreCase(valorBuscado);
            case "rol":
                return this.getRol().getName().compareToIgnoreCase(campo);

            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }*/
}
