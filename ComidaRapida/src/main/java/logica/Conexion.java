package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConnection(){
        Connection conexion = null;
        var baseDatos = "gestion_pedidos";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "";
        // Cargamos la clase del driver en memoria
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en conexión con BDD " + e.getMessage());
        }
        return conexion;
    }
}
