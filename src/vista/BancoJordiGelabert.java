/*
 * Autor: Jordi Gelabert
 * Fecha: 16 Abril 2025
 * Descripción: Clase principal que contiene el menú de opciones del sistema bancario.
 * Tasca bloc 8 programació
 */


package vista;

import controlador.CustomerDAO;
import controlador.AccountDAO;
import modelo.Customer;
import modelo.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BancoJordiGelabert {

    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/accounting";
        String user = "root";
        String password = "Jordi2001";

        CustomerDAO customerDAO = new CustomerDAO(url, user, password);
        AccountDAO accountDAO = new AccountDAO(url, user, password);
        InformeGenerador informeGenerador = new InformeGenerador(customerDAO, accountDAO);

        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 4) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Mostrar 5 cuentas más antiguas");
            System.out.println("2. Generar informe de cliente");
            System.out.println("3. Modificar saldo de cuenta");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        ArrayList<Account> cuentas = accountDAO.obtenerCincoCuentasMasAntiguas();
                        System.out.println("Cuentas más antiguas:");
                        for (Account cuenta : cuentas) {
                            System.out.println("ID: " + cuenta.getAccountId() +
                                    ", Fecha apertura: " + cuenta.getOpenDate() +
                                    ", Saldo: " + cuenta.getAvailBalance());
                        }
                    } catch (Exception e) {
                        System.out.println("Error al obtener cuentas: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Introduzca el ID del cliente: ");
                    int clienteId = scanner.nextInt();
                    try {
                        informeGenerador.generarInforme(clienteId);
                        System.out.println("Informe generado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error al generar el informe: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Introduzca el ID de la cuenta: ");
                    int cuentaId = scanner.nextInt();
                    System.out.print("Cantidad a modificar (puede ser negativa): ");
                    float cantidad = scanner.nextFloat();
                    try {
                        boolean actualizado = accountDAO.modificarSaldoCuenta(cuentaId, cantidad);
                        if (actualizado) {
                            System.out.println("Saldo actualizado correctamente.");
                        } else {
                            System.out.println("No se encontró la cuenta o no se modificó.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al modificar saldo: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}
