/*
 * Autor: Jordi Gelabert
 * Fecha: 16 Abril 2025
 * Descripción: Clase DAO que gestiona el acceso a los datos de las cuentas bancarias.
 * Tasca bloc 8 programació
 */


package controlador;

import modelo.Account;
import java.sql.*;
import java.util.ArrayList;

public class AccountDAO {
    private String url;
    private String user;
    private String password;

    public AccountDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public ArrayList<Account> obtenerCuentasPorClienteId(int custId) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        ArrayList<Account> cuentas = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT ACCOUNT_ID, AVAIL_BALANCE FROM ACCOUNT WHERE CUST_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, custId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Account cuenta = new Account();
                cuenta.setAccountId(rs.getInt("ACCOUNT_ID"));
                cuenta.setAvailBalance(rs.getFloat("AVAIL_BALANCE"));
                cuentas.add(cuenta);
            }
        }

        return cuentas;
    }

    public float obtenerSaldoTotalCliente(int custId) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        float total = 0;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT SUM(AVAIL_BALANCE) FROM ACCOUNT WHERE CUST_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, custId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getFloat(1);
            }
        }

        return total;
    }

    public ArrayList<Account> obtenerCincoCuentasMasAntiguas() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        ArrayList<Account> cuentas = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT ACCOUNT_ID, OPEN_DATE, AVAIL_BALANCE FROM ACCOUNT ORDER BY OPEN_DATE ASC LIMIT 5";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Account cuenta = new Account();
                cuenta.setAccountId(rs.getInt("ACCOUNT_ID"));
                cuenta.setOpenDate(rs.getDate("OPEN_DATE"));
                cuenta.setAvailBalance(rs.getFloat("AVAIL_BALANCE"));
                cuentas.add(cuenta);
            }
        }

        return cuentas;
    }

    public boolean modificarSaldoCuenta(int accountId, float cantidad) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE ACCOUNT SET AVAIL_BALANCE = AVAIL_BALANCE + ? WHERE ACCOUNT_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, cantidad);
            stmt.setInt(2, accountId);
            int filas = stmt.executeUpdate();
            return filas > 0;
        }
    }
}
