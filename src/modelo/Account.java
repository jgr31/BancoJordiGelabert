/*
 * Autor: Jordi Gelabert
 * Fecha: 16 Abril 2025
 * Descripción: Clase modelo que representa una cuenta bancaria.
 * Tasca bloc 8 programació
 */


package modelo;

import java.util.Date;

public class Account {
    private int accountId;
    private double availBalance;
    private Date openDate;

    public Account() {
        // Constructor vacío
    }

    // Constructor, getters y setters
    public Account(int accountId, double availBalance, Date openDate) {
        this.accountId = accountId;
        this.availBalance = availBalance;
        this.openDate = openDate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(double availBalance) {
        this.availBalance = availBalance;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
}
