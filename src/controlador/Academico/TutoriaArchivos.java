package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.DAO.DaoInterface;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import modelo.Modalidad;
import modelo.Tutoria;

public class TutoriaArchivos implements DaoInterface<Tutoria> {

    Conexion instanciaMsql = Conexion.getInstance();
    private DynamicList<Tutoria> tutorias;
    private Tutoria tutoria;

    public TutoriaArchivos() {
        tutorias = new DynamicList<>();
    }

    public TutoriaArchivos(DynamicList<Tutoria> tutorias, Tutoria tutoria) {
        this.tutorias = tutorias;
        this.tutoria = tutoria;
    }

    public DynamicList<Tutoria> getTutorias() {
        tutorias = all();
        return tutorias;
    }

    public void setTutorias(DynamicList<Tutoria> tutorias) {
        this.tutorias = tutorias;
    }

    public Tutoria getTutoria() {
        if (tutoria == null) {
            tutoria = new Tutoria();
        }
        return tutoria;
    }

    public void setTutoria(Tutoria tutoria) {
        this.tutoria = tutoria;
    }

    @Override
    public DynamicList<Tutoria> all() {
        DynamicList<Tutoria> lista = new DynamicList();
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("select *from TUTORIA");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                String fechaHoraString = rs.getString(5);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime fechaHoraLocal = LocalDateTime.parse(fechaHoraString, formatter);
                LocalDate fechaLocal = fechaHoraLocal.toLocalDate();
                Tutoria tutoria = new Tutoria(Integer.parseInt(rs.getString(1)), fechaLocal, rs.getString(3), Modalidad.valueOf((rs.getString(4))), Boolean.parseBoolean(rs.getString(5)), Integer.parseInt(rs.getString(rs.getString(6))));
                lista.add(tutoria);
            }

        } catch (Exception e) {
        }

        return lista;
    }

    public boolean buscarID(String text) throws EmptyException {
        tutorias = all();
        for (int i = 0; i < tutorias.getLength(); i++) {
            if (tutorias.getInfo(i).getId().equals(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean persist(Tutoria data) {
        PreparedStatement consulta = null;
        Connection conexion = null;

        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("INSERT INTO TUTORIA (ID, FECHA, TEMA, MODALIDAD, IMPARTIDA, HORARIO_ID) VALUES (?, ?, ?, ?)");
            consulta.setInt(1, all().getLength() + 1);
            java.sql.Date fechaSQL = java.sql.Date.valueOf(tutoria.getFecha());
            consulta.setDate(2, fechaSQL);
            consulta.setString(3, tutoria.getTema());
            consulta.setString(4, String.valueOf(tutoria.getModalidad()));
            consulta.setBoolean(5, tutoria.getImpartida());
            consulta.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean merge(Tutoria data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Tutoria get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
