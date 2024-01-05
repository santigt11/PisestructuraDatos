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
import modelo.TutoriaMatricula;

public class TutoriaMatriculaArchivos implements DaoInterface<TutoriaMatricula> {

    Conexion instanciaMsql = Conexion.getInstance();
    private DynamicList<TutoriaMatricula> tutorias;
    private TutoriaMatricula tutoria;

    public TutoriaMatriculaArchivos() {
        tutorias = new DynamicList<>();
    }

    public TutoriaMatriculaArchivos(DynamicList<TutoriaMatricula> tutorias, TutoriaMatricula tutoria) {
        this.tutorias = tutorias;
        this.tutoria = tutoria;
    }

    public DynamicList<TutoriaMatricula> getTutorias() {
        tutorias = all();
        return tutorias;
    }

    public void setTutorias(DynamicList<TutoriaMatricula> tutorias) {
        this.tutorias = tutorias;
    }

    public TutoriaMatricula getTutoriaMatricula() {
        if (tutoria == null) {
            tutoria = new TutoriaMatricula();
        }
        return tutoria;
    }

    public void setTutoria(TutoriaMatricula tutoria) {
        this.tutoria = tutoria;
    }

    @Override
    public DynamicList<TutoriaMatricula> all() {
        DynamicList<TutoriaMatricula> lista = new DynamicList();
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
                TutoriaMatricula tutoriaM = new TutoriaMatricula(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)));
                lista.add(tutoriaM);
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
    public Boolean persist(TutoriaMatricula data) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("INSERT INTO TUTORIAMATRICULA (ID, MATRICULA_ID, TUTORIA_ID) VALUES (?, ?, ?)");
            consulta.setInt(1, all().getLength() + 1);
            consulta.setInt(2, data.getIdMatricula());
            consulta.setInt(3, data.getIdTutoria());
            consulta.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean merge(TutoriaMatricula data, Integer index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TutoriaMatricula get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
