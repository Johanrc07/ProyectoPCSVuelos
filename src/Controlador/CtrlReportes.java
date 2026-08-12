package Controlador;

import Modelo.SentenciasReportes;
import Vista.frmReportes;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CtrlReportes implements ActionListener {
    private final frmReportes vista;
    private final SentenciasReportes modelo;

    public CtrlReportes(frmReportes vista, SentenciasReportes modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.btnGenerar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    public void inicio() {
        vista.setTitle("AeroFly - Reportes");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGenerar) generarReporte();
        if (e.getSource() == vista.btnLimpiar) limpiarCampos();
    }

    private void generarReporte() {
        String tipo = vista.txtTipoReporte.getText().trim().toLowerCase();
        if (tipo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Escriba el tipo de reporte.");
            return;
        }

        List<Object[]> datos;
        String[] columnas;
        if (tipo.contains("venta")) {
            columnas = new String[]{"ID Reserva", "Código", "Fecha", "Cliente", "Vuelo", "Asiento", "Ingreso"};
            datos = modelo.obtenerVuelosVendidos();
        } else if (tipo.contains("cancel")) {
            columnas = new String[]{"ID Reserva", "Código", "Fecha", "Estado", "Cliente", "Vuelo", "Asiento"};
            datos = modelo.obtenerCancelaciones();
        } else if (tipo.contains("ingreso")) {
            columnas = new String[]{"Reservas activas", "Ingresos"};
            datos = modelo.obtenerIngresos();
        } else if (tipo.contains("ocup")) {
            columnas = new String[]{"ID Vuelo", "Vuelo", "Capacidad", "Ocupados", "% Ocupación"};
            datos = modelo.obtenerOcupacionVuelos();
        } else if (tipo.contains("admin")) {
            columnas = new String[]{"Estado", "Cantidad"};
            datos = modelo.obtenerConsultasAdministrativas();
        } else if (tipo.contains("historial")) {
            columnas = new String[]{"ID Reserva", "Código", "Fecha", "Estado", "Cliente", "Vuelo", "Asiento"};
            datos = modelo.obtenerHistorialReservas();
        } else if (tipo.contains("reserva")) {
            columnas = new String[]{"ID Reserva", "Código", "Fecha", "Estado", "Cliente", "Vuelo", "Asiento"};
            datos = modelo.obtenerReservas();
        } else {
            JOptionPane.showMessageDialog(vista, "Tipo no reconocido. Use: Ventas, Reservas, Cancelaciones, Ingresos, Ocupacion, Administrativas o Historial.");
            return;
        }

        DefaultTableModel tabla = new DefaultTableModel(columnas, 0);
        for (Object[] fila : datos) tabla.addRow(fila);
        vista.tblReportes.setModel(tabla);
    }

    private void limpiarCampos() {
        vista.txtTipoReporte.setText("");
        vista.txtFechaInicio.setText("");
        vista.txtFechaFin.setText("");
        vista.tblReportes.setModel(new DefaultTableModel());
    }
}
