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
    private String persona_DNI;

    public Usuario() {
    }


    public Usuario(Integer id, String correo, String clave) {
        this.id = id;
        this.correo = correo;
        this.clave = clave;
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


    public String getPersona_DNI() {
        return persona_DNI;
    }

    public void setPersona_DNI(String persona_DNI) {
        this.persona_DNI = persona_DNI;
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

public Boolean compare(Usuario u, String field, Integer type) {
        switch (type) {
            case 0 -> {
                if (field.equalsIgnoreCase("correo")) {
                    return this.getCorreo().toLowerCase().compareTo(u.getCorreo().toLowerCase()) < 0;
                }
                else if (field.equalsIgnoreCase("correo")) {
                    return this.getCorreo().compareTo(u.getCorreo().toLowerCase()) > 0;
                }
            }
             case 1 -> {
                if (field.equalsIgnoreCase("persona_DNI")) {
                    return this.getPersona_DNI().toLowerCase().compareTo(u.getCorreo().toLowerCase()) < 0;
                }
                else if (field.equalsIgnoreCase("persona_DNI")) {
                    return this.getPersona_DNI().compareTo(u.getCorreo().toLowerCase()) > 0;
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
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "correo":
                return this.correo.compareToIgnoreCase(valorBuscado);

            case "clave":
                return this.clave.compareToIgnoreCase(valorBuscado);

            case "idpersona":
                return this.persona_DNI.compareToIgnoreCase(valorBuscado);
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
