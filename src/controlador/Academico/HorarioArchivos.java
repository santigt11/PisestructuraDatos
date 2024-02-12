package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.DAO.DaoInterface;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.Horario;

public class HorarioArchivos implements DaoInterface<Horario> {

    Conexion instanciaMsql = Conexion.getInstance();
    private DynamicList<Horario> horarios;
    private Horario horario;

    public HorarioArchivos() {
        horarios = new DynamicList<>();
    }

    public HorarioArchivos(DynamicList<Horario> horarios, Horario horario) {
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
        if (getHorario() == null) {
            setHorario(new Horario());
        }
        return getHorario();
    }

    public void setCarrera(Horario horario) {
        this.setHorario(horario);
    }

    @Override
    public DynamicList<Horario> all() {
        DynamicList<Horario> lista = new DynamicList();
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("select *from HORARIO");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                Horario horario = new Horario(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), Boolean.parseBoolean(rs.getString(4)));
                lista.add(horario);
            }

        } catch (Exception e) {
        }

        return lista;
    }

    @Override
    public Boolean persist(Horario horario) {
        PreparedStatement consulta = null;
        Connection conexion = null;

        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("INSERT INTO HORARIO (ID, HORAINICIO, HORAFIN, DISPONIBLE) VALUES (?, ?, ?, ?)");
            consulta.setInt(1, all().getLength() + 1);
            consulta.setString(2, horario.getHoraInicio());
            consulta.setString(3, horario.getHoraFin());
            consulta.setBoolean(4, horario.getDisponible());
            consulta.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean merge(Horario data, String codigo) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("UPDATE horario SET HORAINICIO = ?, HORAFIN = ?, DISPONIBLE = ?, WHERE id = ?;");
            consulta.setString(1, data.getHoraInicio());
            consulta.setString(2, data.getHoraFin());
            consulta.setBoolean(3, data.getDisponible());
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean buscarID(String text) throws EmptyException {
        horarios = all();
        for (int i = 0; i < horarios.getLength(); i++) {
            if (horarios.getInfo(i).getId().equals(text)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Horario get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean merge(Horario data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
