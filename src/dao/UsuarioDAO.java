/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.ConnectionDados;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

/**
 *
 * @author Evair Daniel
 */
public class UsuarioDAO {

    public boolean checkLogin(String usuario, String senha) {

        Connection con = ConnectionDados.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_usuario WHERE usuario = ? AND senha = ?");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDados.closeConnection(con, stmt, rs);
        }

        return check;

    }
}
