/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import controlador.DAO.Conexion;
import controlador.DAO.DaoInterface;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase adaptadora para los metodos de guardar, modificar, listar y buscar por
 * id desde la Base de datos
 *
 * @author infierno
 */
public class AdaptadorDao<T> implements DaoInterface<T> {

    /**
     * Obejto Conexion
     */
    public final Conexion conexion;
    /**
     * Class del modelo a usar
     */
    public Class clazz;

    /**
     * Constructor de la clase
     *
     * @param clazz El objeto de la clase del modelo Ejemplo: Persona.class
     */
    public AdaptadorDao(Class clazz) {
        this.conexion = new Conexion();
        this.clazz = clazz;
    }

    /**
     * Metodo que permite guardar
     *
     * @param obj El objeto del modelo lleno
     * @return La llave primaria generada por el motor de base de datos (se
     * sugiere construir la tabla de base de datos con la generacion de id auto
     * incementable)
     * @throws Exception Cuando no se puede guardar en la base de datos
     */
    @Override
    public Boolean persist(T obj) {
        String query = queryInsert(obj);

        try (PreparedStatement statement = conexion.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al persistir objeto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conexion.getConnection() != null) {
                    conexion.getConnection().close();
                    conexion.setConnection(null);
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Metodo que permite modificar un registro en la base de datos, para
     * modificar se debe primero consultar el Objeto haciendo uso del metodo
     * Obtener
     *
     * @param obj El objeto del modelo a modificar
     * @throws Exception Alguna Excepcion si no modifica
     */
    @Override
    public void merge(T obj) throws Exception {
        String query = queryUpdate(obj);
        Statement st = conexion.getConnection().createStatement();
        st.executeUpdate(query);
        conexion.getConnection().close();
        conexion.setConnection(null);
    }

    /**
     * Metodo que permite sacar los datos de la base de datos
     *
     * @return Un Objeto de la ListaEnlazada con los datos llenos
     */
    @Override
    public DynamicList<T> all() {

        DynamicList<T> lista = new DynamicList<>();
        try {
            Statement stmt = conexion.getConnection().createStatement();
            String query = "SELECT * FROM " + clazz.getSimpleName().toUpperCase();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lista.add(llenarObjeto(rs));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    /**
     * Permite obtener un objeto de la base de datos a travez del Id
     *
     * @param id El id a buscar en la base de datos
     * @return El objeto buscado, es null si no esxiste el objeto
     */
    @Override
    public T get(Integer id) {
        T data = null;
        try {
            Statement stmt = conexion.getConnection().createStatement();
            String query = "select * from " + clazz.getSimpleName().toLowerCase() + " where id = " + id;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data = llenarObjeto(rs);
            }
        } catch (Exception e) {
        }
        return data;
    }

    //--------------ESTO ES DEL CRUD NO MODIFICAR AL MENOS QUE LO AMERITE------
    private T llenarObjeto(ResultSet rs) {
        T data = null;
        try {
            data = (T) clazz.getDeclaredConstructor().newInstance();
            for (Field f : clazz.getDeclaredFields()) {
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                fijarDatos(f, rs, data, atributo);
            }
            for (Field f : clazz.getSuperclass().getDeclaredFields()) {
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                fijarDatos(f, rs, data, atributo);
            }

        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return data;
    }

    private void fijarDatos(Field f, ResultSet rs, T data, String atributo) {
        try {
            Method m = null;

            if (f.getType().getSimpleName().equalsIgnoreCase("String")) {
                m = clazz.getMethod("set" + atributo, String.class);
                m.invoke(data, rs.getString(atributo));
            }

            if (f.getType().isEnum()) {
                m = clazz.getMethod("set" + atributo, (Class<Enum>) f.getType());
                m.invoke(data, Enum.valueOf((Class<Enum>) f.getType(), rs.getString(atributo)));
            }

            if (f.getType().getSimpleName().equalsIgnoreCase("Integer")) {
                m = clazz.getMethod("set" + atributo, Integer.class);
                m.invoke(data, rs.getInt(atributo));
            }

            if (f.getType().getSimpleName().equalsIgnoreCase("Double")) {
                m = clazz.getMethod("set" + atributo, Double.class);
                m.invoke(data, rs.getDouble(atributo));
            }

            if (f.getType().getSimpleName().equalsIgnoreCase("Boolean")) {
                m = clazz.getMethod("set" + atributo, Boolean.class);
                Integer booleanValue = rs.getInt(atributo);
                Boolean convertedValue = (booleanValue == 1);
                m.invoke(data, convertedValue);
            }

            if (f.getType().getSimpleName().equalsIgnoreCase("Date")) {
                m = clazz.getMethod("set" + atributo, Date.class);
                java.sql.Date sqlDate = rs.getDate(atributo);
                LocalDate localDate = sqlDate.toLocalDate();  // Posible punto de error
                m.invoke(data, localDate);
            }

            if (f.getType().getSimpleName().equalsIgnoreCase("LocalDate")) {
                LocalDate localDate = rs.getDate(atributo).toLocalDate();
                m = clazz.getMethod("set" + atributo, LocalDate.class);
                m.invoke(data, localDate);
            }
        } catch (Exception e) {
            System.out.println("Fijar datos error " + e);
        }
    }

    private HashMap<String, Object> obtenerObjeto(T obj) {
        HashMap<String, Object> mapa = new HashMap<>();
        try {
            for (Field f : clazz.getDeclaredFields()) {
                Method m = null;
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                m = clazz.getMethod("get" + atributo);
                Object aux = m.invoke(obj);
                if (aux != null) {
                    mapa.put(atributo.toLowerCase(), aux);
                }
            }

            for (Field f : clazz.getSuperclass().getDeclaredFields()) {
                Method m = null;
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                m = clazz.getMethod("get" + atributo);
                Object aux = m.invoke(obj);
                if (aux != null) {
                    mapa.put(atributo.toLowerCase(), aux);
                }

            }
        } catch (Exception e) {
            System.out.println("No se pudo tener dato");
        }
        return mapa;
    }

    private String queryInsert(T obj) {
        HashMap<String, Object> mapa = obtenerObjeto(obj);
        String query = "INSERT INTO " + clazz.getSimpleName().toLowerCase() + " (";
        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            query += entry.getKey() + ",";

        }
        query = query.substring(0, query.length() - 1);
        query += ") VALUES (";
        for (Map.Entry<String, Object> entry : mapa.entrySet()) {

            if (entry.getValue().getClass().getSuperclass().getSimpleName().equalsIgnoreCase("Number")) {
                query += entry.getValue().toString() + ", ";
            }

            if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Boolean")) {
                boolean booleanValue = (Boolean) entry.getValue();
                int booleanIntValue = booleanValue ? 1 : 0;
                query += booleanIntValue + ", ";
            }

            if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Date")) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dateValue = (Date) entry.getValue();
                String formattedDate = formato.format(dateValue);
                query += "TO_DATE('" + formattedDate + "', 'YYYY-MM-DD HH24:MI:SS'), ";
            }
            if (entry.getValue().getClass().isEnum() || entry.getValue().getClass().getSimpleName().equalsIgnoreCase("String")) {
                query += "'" + entry.getValue().toString() + "'" + ", ";
            }

            if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("LocalDate")) {
                LocalDate localDate = (LocalDate) entry.getValue();
                String formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
                query += "TO_DATE('" + formattedDate + "', 'yyyy-MM-dd'), ";
                System.out.println(query);
            }
        }
        query = query.substring(0, query.length() - 2);
        query += ")";
        return query;
    }

    private String queryUpdate(T obj) {
        HashMap<String, Object> mapa = obtenerObjeto(obj);
        String query = "UPDATE " + clazz.getSimpleName().toLowerCase() + " SET ";
        Integer id = 0;
        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            if (entry.getKey().toString().equalsIgnoreCase("id")) {
                id = (Integer) entry.getValue();
            } else {
                query += entry.getKey() + " = ";
                if (entry.getValue().getClass().getSuperclass().getSimpleName().equalsIgnoreCase("Number") || entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Boolean")) {
                    query += entry.getValue() + ", ";
                }
                if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Date")) {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    query += '"' + formato.format(entry.getValue()) + '"' + ", ";
                }
                if (entry.getValue().getClass().isEnum() || entry.getValue().getClass().getSimpleName().equalsIgnoreCase("String")) {
                    query += "'" + entry.getValue().toString() + "'" + ", ";
                }

                if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("LocalDate")) {
                    LocalDate localDate = (LocalDate) entry.getValue();
                    String formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
                    query += "'" + formattedDate + "'" + ", ";
                }
            }

        }

        query += "";

        query = query.substring(0, query.length() - 2);
        query += " WHERE id = " + id;
        return query;
    }
}
