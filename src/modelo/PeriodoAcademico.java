package modelo;

import java.time.LocalDate;

public class PeriodoAcademico {

    private Integer id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean estadoActivo;

    public PeriodoAcademico() {
    }
    
    public PeriodoAcademico(Integer id, LocalDate fechaInicio, LocalDate fechaFin, Boolean estadoActivo) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoActivo = estadoActivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(Boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
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
