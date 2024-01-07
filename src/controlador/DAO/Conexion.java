/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
/**
 *
 * @author Santiago
 */
public class Conexion {
    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    public static final String USERNAME = "SANTIAGO";
    public static final String PASSWORD = "santi1809";
    public static Connection instancia;

    public Conexion() {
    }
    
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    
    
    public void desconectar(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public void cerrarResultado(ResultSet resultado) {
        try {
            resultado.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar el resultado: " + e.getMessage());
        }
    }

    public void cerrarStatment(PreparedStatement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar el statement: " + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        if (instancia == null)
            instancia = conectar();
        return instancia;
    }
    
    public void setConnection(Connection connection) {
        this.instancia = connection;
    }
}
