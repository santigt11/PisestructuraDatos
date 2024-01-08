/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Santiago
 */
public class Horario {

    private Integer id;
    private String horaInicio;
    private String horaFin;
    private Boolean disponible;

    public Horario() {
    }

    public Horario(Integer id, String horaInicio, String horaFin, Boolean disponible) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponible = disponible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "horaInicio":
                return this.horaInicio.compareToIgnoreCase(valorBuscado);
            case "horaFin":
                return this.horaFin.compareToIgnoreCase(valorBuscado);
            case "disponible":
                return this.disponible == Boolean.parseBoolean(valorBuscado) ? 0 : 1;
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }

    @Override
    public String toString() {
        return horaInicio + " - " + horaFin;
    }
    
}
