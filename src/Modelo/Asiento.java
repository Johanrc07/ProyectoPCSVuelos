/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jrodriguez
 */
public class Asiento {
    
   
    private int idAsiento;
    private int numero;
    private String estado;
    private int idAvion;

    // Constructor vacío
    public Asiento() {
    }

    // Constructor lleno
    public Asiento(int idAsiento, int numero, String estado, int idAvion) {
        this.idAsiento = idAsiento;
        this.numero = numero;
        this.estado = estado;
        this.idAvion = idAvion;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }
}

