package modelo;

import java.time.LocalDate;

public class PeriodoAcademico {

    private Integer id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public PeriodoAcademico(Integer id, LocalDate fechaIncio, LocalDate fechaFin) {
        this.id = id;
        this.fechaInicio = fechaIncio;
        this.fechaFin = fechaFin;
    }

    public PeriodoAcademico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaIncio() {
        return fechaInicio;
    }

    public void setFechaIncio(LocalDate fechaIncio) {
        this.fechaInicio = fechaIncio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return fechaInicio + "  -  " + fechaFin;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "fechainicio":
                return this.fechaInicio.compareTo(LocalDate.parse(valorBuscado));
            case "fechafin":
                return this.fechaFin.compareTo(LocalDate.parse(valorBuscado));
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
