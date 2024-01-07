package controlador.Academico;


import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import modelo.Horario;

public class HorarioArchivos extends AdaptadorDao<Horario> {
    
    private DynamicList<Horario> horarios;
    private Horario horario;

    public HorarioArchivos() {
        super(Horario.class);
    }

    public HorarioArchivos(DynamicList<Horario> horarios, Horario horario, Class clazz) {
        super(clazz);
        this.horarios = horarios;
        this.horario = horario;
    }

    public DynamicList<Horario> getHorarios() {
        return horarios;
    }
    
    public DynamicList<Horario> getHorariosTodos() {
        horarios = all();
        return horarios;
    }

    public void setHorarios(DynamicList<Horario> horarios) {
        this.horarios = horarios;
    }

    public Horario getHorario() {
        if (horario == null) {
            horario = new Horario();
        }
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    @Override
    public Integer persist(Horario obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
