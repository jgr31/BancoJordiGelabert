/*
 * Autor: Jordi Gelabert
 * Fecha: 16 Abril 2025
 * Descripción: Clase encargada de generar informes en formato .txt para un cliente específico.
 * Tasca bloc 8 programació
 */


package vista;

import controlador.CustomerDAO;
import controlador.AccountDAO;
import modelo.Customer;
import modelo.Account;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class InformeGenerador {

    private final CustomerDAO customerDAO;
    private final AccountDAO accountDAO;

    public InformeGenerador(CustomerDAO customerDAO, AccountDAO accountDAO) {
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
    }

    public void generarInforme(int customerId) {
        Customer customer = null;
        ArrayList<Account> cuentas = null;

        try {
            customer = customerDAO.obtenerClientePorId(customerId);
            cuentas = accountDAO.obtenerCuentasPorClienteId(customerId);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al obtener datos de la base de datos: " + e.getMessage());
            return;
        }

        try (FileWriter writer = new FileWriter("informe_cliente_" + customerId + ".txt")) {
            if (customer != null) {
                writer.write("ID Cliente: " + customer.getCustId() + "\n");
                writer.write("Ciudad: " + customer.getCity() + "\n");
            }

            double saldoTotal = 0;
            if (cuentas != null) {
                for (Account cuenta : cuentas) {
                    writer.write("Cuenta: " + cuenta.getAccountId() + ", Saldo: " + cuenta.getAvailBalance() + "\n");
                    saldoTotal += cuenta.getAvailBalance();
                }
            }

            writer.write("Saldo Total: " + saldoTotal + "\n");
            writer.write("Generado por: Jordi Gelabert - " + new java.util.Date() + "\n");

        } catch (IOException e) {
            System.err.println("Error al generar el informe: " + e.getMessage());
        }
    }
}


