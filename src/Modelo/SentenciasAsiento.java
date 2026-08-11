/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jrodriguez
 */
import java.sql.*;

public class SentenciasAsiento extends Conexion {
    
      public ResultSet todosAsientos() {

        String sql = "SELECT * FROM asiento";

        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al consultar asientos: " + e);
            return null;
        }
    }

    public ResultSet asientosPorAvion(int idAvion) {

        String sql = "SELECT * FROM asiento WHERE idAvion=?";

        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idAvion);

            return ps.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al consultar asientos por avión: " + e);
            return null;
        }
    }

    public boolean buscar(Asiento asiento) {

        String sql = "SELECT * FROM asiento WHERE idAsiento=?";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, asiento.getIdAsiento());

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    asiento.setIdAsiento(rs.getInt("idAsiento"));
                    asiento.setNumero(rs.getInt("numero"));
                    asiento.setEstado(rs.getString("estado"));
                    asiento.setIdAvion(rs.getInt("idAvion"));

                    return true;
                }
            }

            return false;

        } catch (SQLException e) {
            System.err.println("Error al buscar asiento: " + e);
            return false;
        }
    }
    public boolean actualizarEstado(Asiento asiento) {

    String sql = "UPDATE asiento SET estado=? WHERE idAsiento=?";

    try (Connection con = getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, asiento.getEstado());
        ps.setInt(2, asiento.getIdAsiento());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.err.println("Error al actualizar estado del asiento: " + e);
        return false;
    }
    }
}