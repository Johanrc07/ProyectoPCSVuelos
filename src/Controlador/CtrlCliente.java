/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.SentenciasCliente;
import Vista.frmCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author isabella
 */
public class CtrlCliente implements ActionListener {

    private final Cliente modelo;
    private final SentenciasCliente consultas;
    private final frmCliente vista;

    public CtrlCliente(Cliente modelo, SentenciasCliente consultas, frmCliente vista) {

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
        vista.setTitle("Control de Clientes");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void Limpiar() {
        vista.txtIdCliente.setText("");
        vista.txtPasaporte.setText("");
        vista.txtTelefono.setText("");
        vista.txtIdUsuario.setText("");
        vista.txtIdCliente.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {

        //guardar
        if (e.getSource() == vista.btnGuardar) {
            modelo.setPasaporte(vista.txtPasaporte.getText());
            modelo.setTelefono(vista.txtTelefono.getText());
            modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));

            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "Cliente guardado correctamente");
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
        }

        //modificar
        if (e.getSource() == vista.btnModificar) {
            modelo.setIdCliente(Integer.parseInt(vista.txtIdCliente.getText()));
            modelo.setPasaporte(vista.txtPasaporte.getText());
            modelo.setTelefono(vista.txtTelefono.getText());
            modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));

            if (consultas.modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }

        //buscar
        if (e.getSource() == vista.btnBuscar) {
            modelo.setIdCliente(Integer.parseInt(vista.txtIdCliente.getText()));

            if (consultas.buscar(modelo)) {
                vista.txtIdCliente.setText(String.valueOf(modelo.getIdCliente()));
                vista.txtPasaporte.setText(modelo.getPasaporte());
                vista.txtTelefono.setText(modelo.getTelefono());
                vista.txtIdUsuario.setText(String.valueOf(modelo.getIdUsuario()));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el cliente");
                Limpiar();
            }
        }

        //limpiar
        if (e.getSource() == vista.btnLimpiar) {
            Limpiar();
        }
        
        
        //eliminar
        if (e.getSource() == vista.btnEliminar) {
            modelo.setIdCliente(Integer.parseInt(vista.txtIdCliente.getText()));

            if (consultas.eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        }

    }
}
