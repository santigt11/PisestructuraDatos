package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.DAO.DaoInterface;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
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

    public void setAsignaturas(DynamicList<Horario> horarios) {
        this.horarios = horarios;
    }

    public Horario getAsignatura() {
        if (horario == null) {
            horario = new Horario();
        }
        return horario;
    }

    public void setCarrera(Horario horario) {
        this.horario = horario;
    }
    
    @Override
    public Integer persist(Horario obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
