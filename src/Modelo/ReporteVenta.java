
package modelo;

import java.sql.Timestamp;

public class ReporteVenta {
    private int idReserva;
    private String cliente;
    private String codigoVuelo;
    private Timestamp fechaReserva;
    private double totalPagado;

    public ReporteVenta(int idReserva, String cliente, String codigoVuelo, Timestamp fechaReserva, double totalPagado) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.codigoVuelo = codigoVuelo;
        this.fechaReserva = fechaReserva;
        this.totalPagado = totalPagado;
    }

    // Getters y Setters
    public int getIdReserva() { return idReserva; }
    public String getCliente() { return cliente; }
    public String getCodigoVuelo() { return codigoVuelo; }
    public Timestamp getFechaReserva() { return fechaReserva; }
    public double getTotalPagado() { return totalPagado; }
}