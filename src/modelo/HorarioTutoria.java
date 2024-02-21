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

    //metodo para comparar atributos de dos personas basado en un campo y tipo específico
    public Boolean compare(HorarioTutoria p, String field, Integer type) {
        //0 menor 1 mayor
        switch (type) {
            case 0:// Si el tipo es 0 es menor
                if (field.equalsIgnoreCase("horainicio")) {
                    return horaInicio.compareTo(p.getHoraInicio()) < 0;//compara
                } else if (field.equalsIgnoreCase("horafin")) {
                    return horaFin.compareTo(p.getHoraFin()) < 0;
                } else if (field.equalsIgnoreCase("usuario_id")) {
                    return usuario_ID.compareTo(p.getUsuario_ID()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(p.getId()) < 0;
                } else if (field.equalsIgnoreCase("dia")) {
                    return dia_ID.compareTo(p.getDia_ID()) < 0;
                }
            case 1: //si el tipo es 1 es mayor
                if (field.equalsIgnoreCase("horainicio")) {
                    return horaInicio.compareTo(p.getHoraInicio()) > 0;//compara
                } else if (field.equalsIgnoreCase("horafin")) {
                    return horaFin.compareTo(p.getHoraFin()) > 0;
                } else if (field.equalsIgnoreCase("usuario_id")) {
                    return usuario_ID.compareTo(p.getUsuario_ID()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(p.getId()) > 0;
                }else if (field.equalsIgnoreCase("dia")) {
                    return dia_ID.compareTo(p.getDia_ID()) > 0;
                }
            default:
                throw new AssertionError();
        }
    }

    // Método para comparar un campo específico de la persona con un valor buscado
    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "horainicio":
                return this.horaInicio.compareToIgnoreCase(valorBuscado);//Compara el nombre ignorando mayúsculas/minúsculas
            case "horafin":
                return this.horaFin.compareToIgnoreCase(valorBuscado);
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "usuario_id":
                return this.usuario_ID.compareTo(Integer.parseInt(valorBuscado));
            case "dia":
                return this.dia_ID.compareTo(Integer.parseInt(valorBuscado));
            default:
                //Lanza una excepción si el campo no es válido
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }

}
