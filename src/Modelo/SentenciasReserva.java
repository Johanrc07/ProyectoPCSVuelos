package Modelo;

import java.sql.*;
import java.util.UUID;

public class SentenciasReserva extends Conexion {

    // Registrar una reserva
    public boolean registrar(Reserva reserva) {

        // Primero verificamos que el asiento no esté reservado
        String verificar = "SELECT * FROM reserva "
                + "WHERE idVuelo=? "
                + "AND idAsiento=? "
                + "AND estado='ACTIVA'";

        String sql = "INSERT INTO reserva "
                + "(codigoReserva, estado, idCliente, idVuelo, idAsiento) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = getConexion();
             PreparedStatement psVerificar = con.prepareStatement(verificar)) {

            psVerificar.setInt(1, reserva.getIdVuelo());
            psVerificar.setInt(2, reserva.getIdAsiento());

            try (ResultSet rs = psVerificar.executeQuery()) {

                if (rs.next()) {
                    System.err.println("El asiento ya está reservado para este vuelo.");
                    return false;
                }
            }

            String codigo = generarCodigoReserva();

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, codigo);
                ps.setString(2, "ACTIVA");
                ps.setInt(3, reserva.getIdCliente());
                ps.setInt(4, reserva.getIdVuelo());
                ps.setInt(5, reserva.getIdAsiento());

                if (ps.executeUpdate() > 0) {
                    reserva.setCodigoReserva(codigo);
                    reserva.setEstado("ACTIVA");
                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al registrar reserva: " + e);
        }

        return false;
    }

    // Generar código de reserva
    public String generarCodigoReserva() {

        String codigo = UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        return "AF-" + codigo;
    }

    // Consultar todas las reservas
    public ResultSet todasReservas() {

        String sql = "SELECT * FROM reserva";

        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al consultar reservas: " + e);
            return null;
        }
    }

    // Buscar una reserva
    public boolean buscar(Reserva reserva) {

        String sql = "SELECT * FROM reserva WHERE idReserva=?";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reserva.getIdReserva());

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    reserva.setIdReserva(rs.getInt("idReserva"));
                    reserva.setCodigoReserva(rs.getString("codigoReserva"));
                    reserva.setFechaReserva(rs.getString("fechaReserva"));
                    reserva.setEstado(rs.getString("estado"));
                    reserva.setIdCliente(rs.getInt("idCliente"));
                    reserva.setIdVuelo(rs.getInt("idVuelo"));
                    reserva.setIdAsiento(rs.getInt("idAsiento"));

                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar reserva: " + e);
        }

        return false;
    }

    // Modificar una reserva
    public boolean modificar(Reserva reserva) {

        String sql = "UPDATE reserva SET idCliente=?, "
                + "idVuelo=?, idAsiento=? "
                + "WHERE idReserva=?";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reserva.getIdCliente());
            ps.setInt(2, reserva.getIdVuelo());
            ps.setInt(3, reserva.getIdAsiento());
            ps.setInt(4, reserva.getIdReserva());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al modificar reserva: " + e);
            return false;
        }
    }

    // Cancelar una reserva
    public boolean cancelar(Reserva reserva) {

        String sql = "UPDATE reserva SET estado='CANCELADA' "
                + "WHERE idReserva=?";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reserva.getIdReserva());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al cancelar reserva: " + e);
            return false;
        }
    }

    // Verificar si un asiento está ocupado en determinado vuelo
    public boolean asientoOcupado(int idVuelo, int idAsiento) {

        String sql = "SELECT * FROM reserva "
                + "WHERE idVuelo=? "
                + "AND idAsiento=? "
                + "AND estado='ACTIVA'";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idVuelo);
            ps.setInt(2, idAsiento);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar disponibilidad: " + e);
            return true;
        }
    }
}

