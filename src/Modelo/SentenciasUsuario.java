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
public class SentenciasUsuario extends Conexion {

    public boolean registrar(Usuario usu) {
        String sql = "INSERT INTO usuario (nombre, correo, contraseña, rol) VALUES (?,?,?,?)";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getCorreo());
            ps.setString(3, usu.getContraseña());
            ps.setString(4, usu.getRol());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e);
            return false;
        }
    }

    public boolean modificar(Usuario usu) {
        String sql = "UPDATE usuario SET nombre=?, correo=?, contraseña=?, rol=? WHERE idUsuario=?";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getCorreo());
            ps.setString(3, usu.getContraseña());
            ps.setString(4, usu.getRol());
            ps.setInt(5, usu.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al modificar usuario: " + e);
            return false;
        }
    }

    public boolean eliminar(Usuario usu) {
        String sql = "DELETE FROM usuario WHERE idUsuario=?";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usu.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e);
            return false;
        }
    }

    public boolean buscar(Usuario usu) {
        String sql = "SELECT * FROM usuario WHERE idUsuario=?";

        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usu.getIdUsuario());

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    usu.setIdUsuario(rs.getInt("idUsuario"));
                    usu.setNombre(rs.getString("nombre"));
                    usu.setCorreo(rs.getString("correo"));
                    usu.setContraseña(rs.getString("contraseña"));
                    usu.setRol(rs.getString("rol"));

                    return true;
                }
            }

            return false;

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e);
            return false;
        }
    }

    public ResultSet todosUsuarios() {
        String sql = "SELECT * FROM usuario";

        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al consultar usuarios: " + e);
            return null;
        }
    }

    public boolean validar(Usuario usu) {
    String sql = "SELECT * FROM usuario WHERE correo=? AND contraseña=?";

    try (Connection con = getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, usu.getCorreo());
        ps.setString(2, usu.getContraseña());

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                usu.setIdUsuario(rs.getInt("idUsuario"));
                usu.setNombre(rs.getString("nombre"));
                usu.setCorreo(rs.getString("correo"));
                usu.setContraseña(rs.getString("contraseña"));
                usu.setRol(rs.getString("rol"));
                return true;
            }
        }

        return false;

    } catch (SQLException e) {
        System.err.println("Error al validar usuario: " + e);
        return false;
    }
}
    
    
}
