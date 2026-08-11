/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.SentenciasCliente;
import Modelo.Usuario;
import Modelo.SentenciasUsuario;
import Vista.frmMenu;
import Vista.frmUsuario;
import Vista.frmCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author isabella
 */

public class CtrlMenu implements ActionListener {

    private final frmMenu vista;

    public CtrlMenu(frmMenu vista) {
        this.vista = vista;

        this.vista.menuItemUsuarios.addActionListener(this);
        this.vista.menuItemClientes.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
}
    public void inicio() {
        vista.setTitle("AeroFly - Menú Principal");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.menuItemUsuarios) {

            Usuario modelo = new Usuario();
            SentenciasUsuario consultas = new SentenciasUsuario();
            frmUsuario vistaUsuario = new frmUsuario();

            CtrlUsuario controlador = new CtrlUsuario(modelo, consultas, vistaUsuario);

            controlador.inicio();
        }

       if (e.getSource() == vista.menuItemClientes) {

            Cliente modelo = new Cliente();
            SentenciasCliente consultas = new SentenciasCliente();
            frmCliente vistaCliente = new frmCliente();

            CtrlCliente controlador = new CtrlCliente(modelo, consultas, vistaCliente);

            controlador.inicio();
        }
       
        if (e.getSource() == vista.btnSalir) {
            System.exit(0);
        }
        
    }
}
