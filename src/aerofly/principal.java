/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aerofly;

import Modelo.Cliente;
import Modelo.SentenciasCliente;
import Vista.frmUsuario;
import Vista.frmCliente;
import Controlador.CtrlCliente;

public class principal {

    public static void main(String[] args) {

        Cliente modelo = new Cliente();
        SentenciasCliente consultas = new SentenciasCliente();
        frmCliente vista = new frmCliente();

        CtrlCliente controlador = new CtrlCliente(modelo, consultas, vista);

        controlador.inicio();
    }
}




