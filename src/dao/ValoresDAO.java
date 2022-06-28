/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bean.Valores;
import connection.ConnectionDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Evair Daniel
 */
public class ValoresDAO {

    public List<Valores> read() {

        Connection con = ConnectionDados.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Valores> listaval = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_valor");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Valores val = new Valores();
                val.setId(rs.getInt("id"));
                val.setValor_primeira_hora(rs.getFloat("valor_primeira_hora"));
                val.setValor_demais_horas(rs.getFloat("valor_demais_horas"));
                val.setData_fim(rs.getString("data_fim"));

                listaval.add(val);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDados.closeConnection(con, stmt, rs);
        }
        return listaval;
    }
     public boolean create() {
      
     Connection con = ConnectionDados.getConnection();
        PreparedStatement stmt = null;
        boolean create = false;
        try {
            stmt = con.prepareStatement("INSERT IGNORE INTO tbl_valor(id, valor_primeira_hora, valor_demais_horas) VALUES (1, ?, ?)");
            stmt.setFloat(1, 6f);
            stmt.setFloat(2, 4f);

            stmt.executeUpdate();
            create = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar " + ex);
        } finally {
            ConnectionDados.closeConnection(con, stmt);
        }
       
    
        
       return create;
   
}
}
