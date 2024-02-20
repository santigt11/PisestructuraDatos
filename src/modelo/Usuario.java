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
    private Boolean activo;
    private String persona_DNI;
    private Integer rol_id;

    public Usuario() {
    }

    public Usuario(Integer id, Boolean activo, String persona_DNI, Integer rol_id) {
        this.id = id;
        this.activo = activo;
        this.persona_DNI = persona_DNI;
        this.rol_id = rol_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getPersona_DNI() {
        return persona_DNI;
    }

    public void setPersona_DNI(String persona_DNI) {
        this.persona_DNI = persona_DNI;
    }

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    public Boolean compare(Usuario u, String field, Integer type) {
        switch (type) {
            case 0 -> {
                if (field.equalsIgnoreCase("persona_dni")) {
                    return this.getPersona_DNI().toLowerCase().compareTo(u.getPersona_DNI().toLowerCase()) < 0;
                } else if (field.equalsIgnoreCase("rol_id")) {
                    return this.getRol_id().compareTo(u.getRol_id()) < 0;
                }   else if (field.equalsIgnoreCase("id")) {
                    return this.getId().compareTo(u.getId()) < 0;
                }
            }
            case 1 -> {
                if (field.equalsIgnoreCase("persona_dni")) {
                    return this.getPersona_DNI().toLowerCase().compareTo(u.getPersona_DNI().toLowerCase()) > 0;
                } else if (field.equalsIgnoreCase("rol_id")) {
                    return this.getRol_id().compareTo(u.getRol_id()) > 0;
                }   else if (field.equalsIgnoreCase("id")) {
                    return this.getId().compareTo(u.getId()) > 0;
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
            case "persona_dni":
                return this.persona_DNI.compareToIgnoreCase(valorBuscado);
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }

    @Override
    public String toString() {
        return persona_DNI;
    }
}
