/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author jrodriguez
 */

import Modelo.Reserva;
import Modelo.SentenciasReserva;
import java.sql.ResultSet;

public class CtrlReserva {
    
    private SentenciasReserva sentencias;

    public CtrlReserva() {
        sentencias = new SentenciasReserva();
    }

    public boolean registrar(Reserva reserva) {
        return sentencias.registrar(reserva);
    }

    public String generarCodigoReserva() {
        return sentencias.generarCodigoReserva();
    }

    public ResultSet todasReservas() {
        return sentencias.todasReservas();
    }

    public boolean buscar(Reserva reserva) {
        return sentencias.buscar(reserva);
    }

    public boolean modificar(Reserva reserva) {
        return sentencias.modificar(reserva);
    }

    public boolean cancelar(Reserva reserva) {
        return sentencias.cancelar(reserva);
    }

    public boolean asientoOcupado(int idVuelo, int idAsiento) {
        return sentencias.asientoOcupado(idVuelo, idAsiento);
    }
    
}
