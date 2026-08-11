/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;
import Vista.frmUsuario;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 *
 * @author isabella
 */
public class CtrlUsuario implements ActionListener {

    private final Usuario modelo;
    private final SentenciasUsuario consultas;
    private final frmUsuario vista;

    public CtrlUsuario(Usuario modelo, SentenciasUsuario consultas, frmUsuario vista) {

        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    public void inicio() {
        vista.setTitle("Control de Usuarios");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void Limpiar() {
        vista.txtIdUsuario.setText("");
        vista.txtNombre.setText("");
        vista.txtCorreo.setText("");
        vista.txtContraseña.setText("");
        vista.txtRol.setText("");
        vista.txtIdUsuario.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {

        //guardar
        if (e.getSource() == vista.btnGuardar) {

            modelo.setNombre(vista.txtNombre.getText());
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setContraseña(vista.txtContraseña.getText());
            modelo.setRol(vista.txtRol.getText());

            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "Usuario guardado correctamente");
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
        }

        //modificar
        if (e.getSource() == vista.btnModificar) {
            try {
                modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));
                modelo.setNombre(vista.txtNombre.getText());
                modelo.setCorreo(vista.txtCorreo.getText());
                modelo.setContraseña(vista.txtContraseña.getText());
                modelo.setRol(vista.txtRol.getText());

                if (consultas.modificar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un número");
            }
        }

        //eliminar
        if (e.getSource() == vista.btnEliminar) {
            try {
                modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));

                if (consultas.eliminar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
            }
        }

        //buscar
        if (e.getSource() == vista.btnBuscar) {
            try {
                modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));

                if (consultas.buscar(modelo)) {

                    vista.txtIdUsuario.setText(String.valueOf(modelo.getIdUsuario()));
                    vista.txtNombre.setText(modelo.getNombre());
                    vista.txtCorreo.setText(modelo.getCorreo());
                    vista.txtContraseña.setText(modelo.getContraseña());
                    vista.txtRol.setText(modelo.getRol());

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el usuario");
                    Limpiar();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
            }
        }

        //limpiar
        if (e.getSource() == vista.btnLimpiar) {
            Limpiar();
        }
    }
}
