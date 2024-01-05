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
import modelo.Persona;
import modelo.Rol;

public class PersonaArchivos implements DaoInterface<Persona> {

    Conexion instanciaMsql = Conexion.getInstance();
    private DynamicList<Persona> personas;
    private Persona persona;

    public PersonaArchivos() {
        personas = new DynamicList<>();
    }

    public PersonaArchivos(DynamicList<Persona> personas, Persona persona) {
        this.personas = personas;
        this.persona = persona;
    }

    public DynamicList<Persona> getPersonas() {
        personas = all();
        return personas;
    }

    public void setPersonas(DynamicList<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public DynamicList<Persona> all() {
        DynamicList<Persona> lista = new DynamicList();
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("select *from PERSONA");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                String fechaHoraString = rs.getString(5);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime fechaHoraLocal = LocalDateTime.parse(fechaHoraString, formatter);
                LocalDate fechaLocal = fechaHoraLocal.toLocalDate();
                Persona persona = new Persona(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), fechaLocal, rs.getString(6), Rol.valueOf(rs.getString(7)));
                lista.add(persona);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    @Override
    public Boolean persist(Persona persona) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("INSERT INTO persona(ID, DNI, NOMBRE, APELLIDO, FECHANACIMIENTO, TELEFONO, ROL) VALUES (?, ?, ?, ?, ?, ?, ?)");
            consulta.setInt(1, all().getLength() + 1);
            consulta.setString(2, persona.getDni());
            consulta.setString(3, persona.getNombre());
            consulta.setString(4, persona.getApellido());
            java.sql.Date fechaSQL = java.sql.Date.valueOf(persona.getFechaNacimientoi());
            consulta.setDate(5, fechaSQL);
            consulta.setString(6, persona.getTelefono());
            consulta.setString(7, String.valueOf(persona.getRol()));
            consulta.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean merge(Persona data, Integer index) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = instanciaMsql.conectar();
            consulta = conexion.prepareStatement("UPDATE persona SET nombre = ?, apellido = ?, WHERE dni = ?;");
            consulta.setString(1, data.getNombre());
            consulta.setString(2, data.getApellido());
            consulta.setString(3, data.getDni());
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean buscarDNI(String text) throws EmptyException {
        personas = all();
        for (int i = 0; i < personas.getLength(); i++) {
            if (personas.getInfo(i).getDni().equals(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Persona get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        PersonaArchivos pa = new PersonaArchivos();
        System.out.println(pa.all().toString());
    }
}
