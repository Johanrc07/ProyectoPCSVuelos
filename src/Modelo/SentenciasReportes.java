package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Consultas del modulo de reportes. */
public class SentenciasReportes extends Conexion {

    private List<Object[]> ejecutar(String sql) {
        List<Object[]> datos = new ArrayList<>();
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            int columnas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) fila[i] = rs.getObject(i + 1);
                datos.add(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error en reporte: " + e.getMessage());
        }
        return datos;
    }

    public List<Object[]> obtenerReservas() {
        return ejecutar("SELECT idReserva, codigoReserva, fechaReserva, estado, idCliente, idVuelo, idAsiento FROM reserva ORDER BY fechaReserva DESC");
    }

    public List<Object[]> obtenerCancelaciones() {
        return ejecutar("SELECT idReserva, codigoReserva, fechaReserva, estado, idCliente, idVuelo, idAsiento FROM reserva WHERE estado='CANCELADA' ORDER BY fechaReserva DESC");
    }

    public List<Object[]> obtenerHistorialReservas() {
        return ejecutar("SELECT r.idReserva, r.codigoReserva, r.fechaReserva, r.estado, r.idCliente, r.idVuelo, r.idAsiento FROM reserva r ORDER BY r.idCliente, r.fechaReserva DESC");
    }

    /** Reporte de ventas usando las reservas activas. El precio se toma de tarifa. */
    public List<Object[]> obtenerVuelosVendidos() {
        String sql = "SELECT r.idReserva, r.codigoReserva, r.fechaReserva, r.idCliente, r.idVuelo, r.idAsiento, t.precio AS ingreso "
                + "FROM reserva r JOIN vuelo v ON r.idVuelo=v.idVuelo "
                + "JOIN tarifa t ON v.idTarifa=t.idTarifa "
                + "WHERE r.estado='ACTIVA' ORDER BY r.fechaReserva DESC";
        return ejecutar(sql);
    }

    public List<Object[]> obtenerIngresos() {
        String sql = "SELECT COUNT(*) AS reservas_activas, COALESCE(SUM(t.precio),0) AS ingresos "
                + "FROM reserva r JOIN vuelo v ON r.idVuelo=v.idVuelo "
                + "JOIN tarifa t ON v.idTarifa=t.idTarifa WHERE r.estado='ACTIVA'";
        return ejecutar(sql);
    }

    /** Ocupacion: cuenta reservas activas por vuelo y obtiene la capacidad desde asiento. */
    public List<Object[]> obtenerOcupacionVuelos() {
        String sql = "SELECT v.idVuelo, v.codigoVuelo, "
                + "COUNT(DISTINCT a.idAsiento) AS capacidad, "
                + "COUNT(DISTINCT CASE WHEN r.estado='ACTIVA' THEN r.idAsiento END) AS ocupados, "
                + "ROUND(COUNT(DISTINCT CASE WHEN r.estado='ACTIVA' THEN r.idAsiento END)*100.0/NULLIF(COUNT(DISTINCT a.idAsiento),0),2) AS porcentaje "
                + "FROM vuelo v LEFT JOIN asiento a ON a.idAvion=v.idAvion "
                + "LEFT JOIN reserva r ON r.idVuelo=v.idVuelo GROUP BY v.idVuelo, v.codigoVuelo ORDER BY v.codigoVuelo";
        return ejecutar(sql);
    }

    public List<Object[]> obtenerConsultasAdministrativas() {
        String sql = "SELECT estado, COUNT(*) AS cantidad FROM reserva GROUP BY estado ORDER BY estado";
        return ejecutar(sql);
    }
}
