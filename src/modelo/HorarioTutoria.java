package modelo;

public class HorarioTutoria {
    private Integer id;
    private String horaInicio;
    private String horaFin;
    private Integer usuario_ID;
    private Integer dia_ID;

    public HorarioTutoria() {
    }

    public HorarioTutoria(Integer id, String horaInicio, String horaFin, Integer usuario_ID, Integer dia_ID) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.usuario_ID = usuario_ID;
        this.dia_ID = dia_ID;
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

    public Integer getUsuario_ID() {
        return usuario_ID;
    }

    public void setUsuario_ID(Integer usuario_ID) {
        this.usuario_ID = usuario_ID;
    }

    public Integer getDia_ID() {
        return dia_ID;
    }

    public void setDia_ID(Integer dia_ID) {
        this.dia_ID = dia_ID;
    }

    @Override
    public String toString() {
        return horaInicio + " - " + horaFin;
    }
    
    
}
