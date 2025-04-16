package controlador;

import modelo.Customer;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {
    private String url;
    private String user;
    private String password;

    public CustomerDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Customer obtenerClientePorId(int id) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        Customer cliente = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT CUST_ID, CITY FROM CUSTOMER WHERE CUST_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Customer();
                cliente.setCustId(rs.getInt("CUST_ID"));
                cliente.setCity(rs.getString("CITY"));
            }
        }

        return cliente;
    }
}

