/*
 * Autor: Jordi Gelabert
 * Fecha: 16 Abril 2025
 * Descripción: Clase modelo que representa un cliente del banco.
 * Tasca bloc 8 programació
 */


package modelo;

public class Customer   {
    private int custId;
    private String city;

    public Customer() {
        // Constructor vacío
    }

    // Constructor, getters y setters
    public Customer(int custId, String city) {
        this.custId = custId;
        this.city = city;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
