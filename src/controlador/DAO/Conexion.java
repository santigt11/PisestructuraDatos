package controlador.DAO;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {
    //Detalles de la base de datos
    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    public static final String USERNAME = "SANTIAGO";
    public static final String PASSWORD = "SANTIAGO1809";

 
    public static Connection instancia;
    
    // Constructor por defecto
    
    public Conexion() {
    }
    //Método privado para establecer una conexión a la base de datos
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);//retorna la conexion
    }
    
    //Método para desconectar la conexión a la base de datos
    public void desconectar(Connection conexion) {
        try {
            conexion.close();//Cierra la conexión
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());//manejo de excepcion
        }
    }
    //Cerrar resultado consulta
    public void cerrarResultado(ResultSet resultado) {
        try {
            resultado.close();//cierra el resultado de la consulta
        } catch (SQLException e) {
            System.out.println("Error al cerrar el resultado: " + e.getMessage());
        }
    }
    
    //Cerrar un statement de una consulta preparada
    public void cerrarStatment(PreparedStatement statement) {
    try {
        if (statement != null) {
            statement.close();
        }
    } catch (SQLException e) {
        System.out.println("Error al cerrar el statement: " + e.getMessage());
    }
}
    //Método para obtener la conexión a la base de datos
    public Connection getConnection() throws SQLException {
        if (instancia == null)
            instancia = conectar();//conecta la instancia de conexion
        return instancia;// Retorna la instancia de la conexión
    }
    //establece conexion
    public void setConnection(Connection connection) {
        this.instancia = connection;
    }
}
