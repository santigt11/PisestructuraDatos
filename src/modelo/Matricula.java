package modelo;

import java.time.LocalDate;

public class Matricula {

    private Integer id;
    private LocalDate fechaRegistro;
    private Boolean expedienteActivo;
    private Integer numero;
    private Integer usuario_ID;
    private Integer periodoAcademico_ID;

    public Matricula(Integer id, LocalDate fechaRegistro, Boolean expedienteActivo, Integer numero, Integer usuario_ID, Integer periodoAcademico_ID) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.expedienteActivo = expedienteActivo;
        this.numero = numero;
        this.usuario_ID = usuario_ID;
        this.periodoAcademico_ID = periodoAcademico_ID;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Matricula() {
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

    public Integer getUsuario_ID() {
        return usuario_ID;
    }

    public void setUsuario_ID(Integer usuario_ID) {
        this.usuario_ID = usuario_ID;
    }

    public Integer getPeriodoAcademico_ID() {
        return periodoAcademico_ID;
    }

    public void setPeriodoAcademico_ID(Integer idPAcademico) {
        this.periodoAcademico_ID = idPAcademico;
    }

    public Boolean getExpedienteActivo() {
        return expedienteActivo;
    }

    public void setExpedienteActivo(Boolean expendienteActivo) {
        this.expedienteActivo = expendienteActivo;
    }

    @Override
    public String toString() {
        return id + " - " + fechaRegistro;
    }

    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "fechaRegistro":
                return this.fechaRegistro.compareTo(LocalDate.parse(valorBuscado));
            case "usuario_id":
                return this.usuario_ID.compareTo(Integer.parseInt(valorBuscado));
            case "periodoacademico_id":
                return this.periodoAcademico_ID.compareTo(Integer.parseInt(valorBuscado));
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
}
