package controlador.Admin;

import controlador.DAO.Conexion;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import modelo.Persona;
import modelo.Rol;

public class PersonaArchivos extends AdaptadorDao<Persona> {

    private DynamicList<Persona> personas;
    private Persona persona;

    public PersonaArchivos() {
        super(Persona.class);
        personas = new DynamicList<>();
    }

    public PersonaArchivos(DynamicList<Persona> personas, Persona persona, Class clazz) {
        super(clazz);
        this.personas = personas;
        this.persona = persona;
    }

    public DynamicList<Persona> getPersonas() {
        return personas;
    }
    
    public DynamicList<Persona> getPersonasTodos() {
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
    public Boolean persist(Persona obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }
    
//    @Override
//    public Integer persist(Persona obj) throws Exception {
//        PreparedStatement consulta = null;
//        try {
//            consulta = conexion.getConnection().prepareStatement("INSERT INTO persona (ID, DNI, NOMBRE, APELLIDO, FECHANACIMIENTO, TELEFONO, ROL) VALUES (?, ?, ?, ?, TO_DATE(?, 'yyyy-MM-dd'), ?, ?)");
//            consulta.setInt(1, all().getLength() + 1);
//            consulta.setString(2, obj.getDni());
//            consulta.setString(3, obj.getNombre());
//            consulta.setString(4, obj.getApellido());
//            consulta.setString(5, obj.getFechaNacimiento().toString());
//            consulta.setString(6, obj.getTelefono());
//            if (obj.getRol().equals(Rol.ESTUDIANTE)) {
//                    consulta.setString(7, Rol.ESTUDIANTE.toString());
//                }else if (obj.getRol().equals(Rol.DOCENTE)){
//                    consulta.setString(7, Rol.DOCENTE.toString());
//                }else{
//                    consulta.setString(7, Rol.ADMINISTRADOR.toString());
//                }
//            consulta.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        
//        return 1;
//    }

    public DynamicList<Persona> buscarLineal(DynamicList<Persona> lista, String campo, String valorBuscado) throws EmptyException {
        Persona personas[] = lista.toArray();
        DynamicList<Persona> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Persona persona = personas[i];
            if (persona.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(persona);
            }
        }
        return listaBusqueda;
    }
    
    
//    @Override
//    public DynamicList<Persona> all() {
//        DynamicList<Persona> lista = new DynamicList<>();
//        try {
//            java.sql.Statement stmt = conexion.getConnection().createStatement();
//            String query = "SELECT * FROM PERSONA";
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                Persona persona = new Persona();
//                persona.setId(rs.getInt(1));
//                persona.setDni(rs.getString(2));
//                persona.setNombre(rs.getString(3));
//                persona.setApellido(rs.getString(4));
//                LocalDate localDate = rs.getDate(5).toLocalDate();
//                persona.setFechaNacimiento(localDate);
//                persona.setTelefono(rs.getString(6));
//                if (rs.getString(7).equalsIgnoreCase("estudiante")) {
//                    persona.setRol(Rol.ESTUDIANTE);
//                }else if (rs.getString(7).equalsIgnoreCase("docente")){
//                    persona.setRol(Rol.DOCENTE);
//                }else{
//                    persona.setRol(Rol.ADMINISTRADOR);
//                }
//                lista.add(persona); 
//            }
//            
//        } catch (Exception e) {
//        }
//        
//        return lista;
//    }
    
    public Persona buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Persona> lista = all();
        int fin = lista.getLength() - 1;
        Persona personas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Persona persona = personas[medio];
            int comparacion = persona.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return persona;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

//    public static void main(String[] args) throws Exception {
//        PersonaArchivos pa = new PersonaArchivos();
//        pa.getPersona().setApellido("Guachizaca");
//        pa.getPersona().setDni("1150357679");
//        pa.getPersona().setFechaNacimiento(new Date(24, 9, 18).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//        System.out.println(pa.getPersona().getFechaNacimiento());
//        pa.getPersona().setNombre("Santiago");
//        pa.getPersona().setRol(Rol.ESTUDIANTE);
//        pa.getPersona().setTelefono("0980170669");
//        pa.persist(pa.getPersona());
//    }
}
