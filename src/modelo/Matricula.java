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
        return id + " - " + fechaRegistro + " " + usuario_ID;
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

    public Boolean compare(Matricula m, String field, Integer type) {
        //0 menor 1 mayor
        switch (type) {
            case 0:// Si el tipo es 0 es menor
                if (field.equalsIgnoreCase("fechaRegistro")) {
                    return fechaRegistro.compareTo(m.getFechaRegistro()) < 0;//compara
                } else if (field.equalsIgnoreCase("numero")) {
                    return numero.compareTo(m.getNumero()) < 0;
                } else if (field.equalsIgnoreCase("periodoacademico_id")) {
                    return periodoAcademico_ID.compareTo(m.getPeriodoAcademico_ID()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(m.getId()) < 0;
                }else if (field.equalsIgnoreCase("usuario_id")) {
                    return usuario_ID.compareTo(m.getUsuario_ID()) < 0;
                }
            case 1: //si el tipo es 1 es mayor
                if (field.equalsIgnoreCase("fechaRegistro")) {
                    return fechaRegistro.compareTo(m.getFechaRegistro()) > 0;//compara
                } else if (field.equalsIgnoreCase("numero")) {
                    return numero.compareTo(m.getNumero()) > 0;
                } else if (field.equalsIgnoreCase("periodoacademico_id")) {
                    return periodoAcademico_ID.compareTo(m.getPeriodoAcademico_ID()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(m.getId()) > 0;
                }else if (field.equalsIgnoreCase("usuario_id")) {
                    return usuario_ID.compareTo(m.getUsuario_ID()) > 0;
                }
            default:
                throw new AssertionError();
        }
    }
}
