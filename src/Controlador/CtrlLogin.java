/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;
import Vista.frmMenu;
import Vista.frmLogin;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 *
 * @author isabella
 */
public class CtrlLogin implements ActionListener {

    private final Usuario modelo;
    private final SentenciasUsuario consultas;
    private final frmLogin vista;

    public CtrlLogin(Usuario modelo, SentenciasUsuario consultas, frmLogin vista) {

        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;

        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    public void inicio() {
        vista.setTitle("Inicio de sesión");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void Limpiar() {
        vista.txtCorreo.setText("");
        vista.txtContraseña.setText("");
        vista.txtCorreo.requestFocus();

    }

    public void actionPerformed(ActionEvent e) {

        //ingresar
        if (e.getSource() == vista.btnIngresar) {

            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setContraseña(new String(vista.txtContraseña.getPassword()));

            if (consultas.validar(modelo)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión correcto");

                frmMenu menu = new frmMenu();
                menu.setLocationRelativeTo(null);
                menu.setVisible(true);

                vista.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
            }
        }

        //limpiar
        if (e.getSource() == vista.btnLimpiar) {
            Limpiar();
        }
    }
}
