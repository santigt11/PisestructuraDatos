package modelo;

import java.time.LocalDate;

public class Contrato {

    private Integer id;
    private LocalDate fechaRegistro;
    private LocalDate fechaCulminacion;
    private String persona_DNI;
    private String asignatura_CODIGO;

    public Contrato() {
    }

    public Contrato(Integer id, LocalDate fechaRegistro, LocalDate fechaCulminacion, String persona_DNI, String asignatura_CODIGO) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.fechaCulminacion = fechaCulminacion;
        this.persona_DNI = persona_DNI;
        this.asignatura_CODIGO = asignatura_CODIGO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaCulminacion() {
        return fechaCulminacion;
    }

    public void setFechaCulminacion(LocalDate fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }

    public String getDniPersona() {
        return persona_DNI;
    }

    public void setDniPersona(String persona_DNI) {
        this.persona_DNI = persona_DNI;
    }

    public String getCodAsignatura() {
        return asignatura_CODIGO;
    }

    public void setCodAsignatura(String asignatura_CODIGO) {
        this.asignatura_CODIGO = asignatura_CODIGO;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "fecharegistro":
                return this.fechaRegistro.compareTo(LocalDate.parse(valorBuscado));
            case "dnidocente":
                return this.persona_DNI.compareToIgnoreCase(valorBuscado);
            case "codasignatura":
                return this.asignatura_CODIGO.compareToIgnoreCase(valorBuscado);
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
