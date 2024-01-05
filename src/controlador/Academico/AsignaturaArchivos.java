package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.DAO.DaoInterface;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.Asignatura;

public class AsignaturaArchivos implements DaoInterface<Asignatura> {

    Conexion instanciaMsql = Conexion.getInstance();
    private DynamicList<Asignatura> asignaturas;
    private Asignatura asignatura;

    public AsignaturaArchivos() {
        asignaturas = new DynamicList<>();
    }

    public AsignaturaArchivos(DynamicList<Asignatura> asignaturas, Asignatura asignatura) {
        this.asignaturas = asignaturas;
        this.asignatura = asignatura;
    }
    
    public DynamicList<Asignatura> getAsignaturasTodas() {
        asignaturas = all();
        return asignaturas;
    }
    
    public DynamicList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(DynamicList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Asignatura getAsignatura() {
        if (asignatura == null) {
            asignatura = new Asignatura();
        }
        return asignatura;
    }

    public void setCarrera(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public DynamicList<Asignatura> all() {
        DynamicList<Asignatura> lista = new DynamicList();
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("select *from ASIGNATURA");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                Asignatura asignatura = new Asignatura(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)));
                lista.add(asignatura);
            }

        } catch (Exception e) {
        }

        return lista;
    }

    @Override
    public Boolean persist(Asignatura asignatura) {
        PreparedStatement consulta = null;
        Connection conexion = null;

        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("INSERT INTO ASIGNATURA (ID, NOMBRE, CODIGO, TOTALHORAS) VALUES (?, ?, ?, ?)");
            consulta.setInt(1, all().getLength() + 1);
            consulta.setString(2, asignatura.getNombre());
            consulta.setString(3, asignatura.getCodigo());
            consulta.setInt(4, asignatura.getTotalHoras());
            consulta.setInt(4, asignatura.getIdMalla());
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean merge(Asignatura data, String codigo) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("UPDATE asignatura SET nombre = ?, totalhoras = ?, WHERE codigo = ?;");
            consulta.setString(1, data.getNombre());
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean buscarCodigo(String text) throws EmptyException {
        asignaturas = all();
        for (int i = 0; i < asignaturas.getLength(); i++) {
            if (asignaturas.getInfo(i).getCodigo().equals(text)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Asignatura get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean merge(Asignatura data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        Asignatura a = new Asignatura(1, "Algebra Lineal", "AL001", 300, 1);
        AsignaturaArchivos aa = new AsignaturaArchivos();
        aa.persist(a);
    }
}
