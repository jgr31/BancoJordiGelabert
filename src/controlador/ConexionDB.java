/*
 * Autor: Jordi Gelabert
 * Fecha: 16 Abril 2025
 * Descripción: Clase que gestiona la conexión a la base de datos MariaDB.
 * Tasca bloc 8 programació
 */


package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mariadb://localhost:3306/accounting";
    private static final String USER = "root";
    private static final String PASSWORD = "Jordi2001";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection con = getConnection();
        if (con != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        }
    }
}
