/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aerofly;

import Modelo.*;
import java.sql.*;
import Modelo.Cliente;
import Modelo.SentenciasCliente;

/**
 *
 * @author isabella
 */
public class principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Cliente cliente = new Cliente();

        cliente.setIdCliente(4);

        SentenciasCliente sentencias = new SentenciasCliente();

        if (sentencias.eliminar(cliente)) {
            System.out.println("Cliente eliminado correctamente");
        } else {
            System.out.println("No se pudo eliminar el cliente");
        }
    }
}
     
