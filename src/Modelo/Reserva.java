/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jrodriguez
 */
public class Reserva {
    
    
    private int idReserva;
    private String codigoReserva;
    private String fechaReserva;
    private String estado;
    private int idCliente;
    private int idVuelo;
    private int idAsiento;
    
        // Constructor vacío
    public Reserva() {
        
    }
         // Constructor lleno
    public Reserva(int idReserva, String codigoReserva, String fechaReserva,
            String estado, int idCliente, int idVuelo, int idAsiento) {

        this.idReserva = idReserva;
        this.codigoReserva = codigoReserva;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idVuelo = idVuelo;
        this.idAsiento = idAsiento;
        
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }
    
   
}

