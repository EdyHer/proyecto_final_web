package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection conexionBD;
    public final String bd = "db_empresa";
    public final String urlConexion = String.format("jdbc:mysql://127.0.0.1:3306/%s", bd);
    public final String usuario = "root";
    public final String contra = "onepiece1";
    public final String jdbc = "com.mysql.cj.jdbc.Driver";

    public void abrir_conexion() {
        try {
            Class.forName(jdbc);
            conexionBD = DriverManager.getConnection(urlConexion, usuario, contra);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    public void cerrar_conexion() {
        try {
            if (conexionBD != null && !conexionBD.isClosed()) {
                conexionBD.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
}
