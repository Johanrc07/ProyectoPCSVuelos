/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;
/**
 *
 * @author isabella
 */
public class SentenciasCliente extends Conexion {
    
   public boolean registrar(Cliente cli) {
    String sql = "INSERT INTO cliente (pasaporte, telefono, idUsuario) VALUES (?,?,?)";

    try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, cli.getPasaporte());
        ps.setString(2, cli.getTelefono());
        ps.setInt(3, cli.getIdUsuario());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.err.println("Error al registrar cliente: " + e);
        return false;
    }
}
   
   
   public boolean modificar(Cliente cli) {
    String sql = "UPDATE cliente SET pasaporte=?, telefono=? WHERE idCliente=?";

    try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, cli.getPasaporte());
        ps.setString(2, cli.getTelefono());
        ps.setInt(3, cli.getIdCliente());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.err.println("Error al modificar cliente: " + e);
        return false;
    }
}
   
   public boolean buscar(Cliente cli) {
    String sql = "SELECT * FROM cliente WHERE idCliente=?";

    try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, cli.getIdCliente());

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                cli.setIdCliente(rs.getInt("idCliente"));
                cli.setPasaporte(rs.getString("pasaporte"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setIdUsuario(rs.getInt("idUsuario"));

                return true;
            }
        }

        return false;

    } catch (SQLException e) {
        System.err.println("Error al buscar cliente: " + e);
        return false;
    }
}
   
   
     public boolean eliminar(Cliente cli) {
    String sql = "DELETE FROM cliente WHERE idCliente=?";

    try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, cli.getIdCliente());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.err.println("Error al eliminar cliente: " + e);
        return false;
    }
}
}
