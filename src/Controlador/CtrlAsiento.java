/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author jrodriguez
 */

import Modelo.Asiento;
import Modelo.SentenciasAsiento;
import java.sql.ResultSet;

public class CtrlAsiento {
    
     private SentenciasAsiento sentencias;

    public CtrlAsiento() {
        sentencias = new SentenciasAsiento();
    }

    public ResultSet todosAsientos() {
        return sentencias.todosAsientos();
    }

    public ResultSet asientosPorAvion(int idAvion) {
        return sentencias.asientosPorAvion(idAvion);
    }

    public boolean buscar(Asiento asiento) {
        return sentencias.buscar(asiento);
    }

    public boolean actualizarEstado(Asiento asiento) {
        return sentencias.actualizarEstado(asiento);
    }
    
}
