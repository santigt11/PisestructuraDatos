package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.DAO.DaoInterface;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.Persona;


public class PersonaArchivos implements DaoInterface<Persona> {
    
    Conexion instanciaMsql = Conexion.getInstance();
    private DynamicList<Persona> asignaturas;
    private Persona persona;

    public PersonaArchivos() {
        asignaturas = new DynamicList<>();
    }

    public PersonaArchivos(DynamicList<Persona> personas, Persona persona) {
        this.asignaturas = personas;
        this.persona = persona;
    }

    public DynamicList<Persona> getPersonas() {
        asignaturas = all();
        return asignaturas;
    }

    public void setAsignaturas(DynamicList<Persona> personas) {
        this.asignaturas = personas;
    }

    public Asignatura getAsignatura() {
        if (persona == null) {
            persona = new Asignatura();
        }
        return persona;
    }

    public void setCarrera(Asignatura asignatura) {
        this.persona = asignatura;
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

    public Boolean merge(Asignatura data, String codigo) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        
        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("UPDATE jugador SET nombre = ?, totalhoras = ?");
            consulta.setString(1, data.getNombre());
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Asignatura get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean merge(Asignatura data, Integer index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public static void main(String[] args) {
        Asignatura a = new Asignatura(1, "Algebra Lineal", "AL001", 300, 1);
        PersonaArchivos aa = new PersonaArchivos();
        aa.persist(a);
    }
}
