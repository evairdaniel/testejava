/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bean.Movimentacao;
import connection.ConnectionDados;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Evair Daniel
 */
public class MovimentacaoDAO {

    public void create(Movimentacao m) {
        Connection con = ConnectionDados.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbl_movimentacao(placa, modelo, data_entrada, data_saida, tempo, valor_pago) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, m.getPlaca());
            stmt.setString(2, m.getModelo());
            stmt.setTimestamp(3, m.getData_entrada());
            stmt.setTimestamp(4, m.getData_saida());
            stmt.setInt(5, m.getTempo());
            stmt.setFloat(6, m.getValor_pago());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar " + ex);
        } finally {
            ConnectionDados.closeConnection(con, stmt);
        }

    }

    public void update(Movimentacao m) {
        Connection con = ConnectionDados.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tbl_movimentacao SET placa = ?, modelo = ? WHERE id = ?");
            stmt.setString(1, m.getPlaca());
            stmt.setString(2, m.getModelo());
            stmt.setInt(3, m.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar " + ex);
        } finally {
            ConnectionDados.closeConnection(con, stmt);
        }

    }

    public void saida(Movimentacao m) {
        Connection con = ConnectionDados.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tbl_movimentacao SET data_saida = ?, tempo = ?, valor_pago = ? WHERE id = ?");
            stmt.setTimestamp(1, m.getData_saida());
            stmt.setInt(2, m.getTempo());
            stmt.setFloat(3, m.getValor_pago());
            stmt.setInt(4, m.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Saída registrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar " + ex);
        } finally {
            ConnectionDados.closeConnection(con, stmt);
        }

    }

    public List<Movimentacao> read() {

        Connection con = ConnectionDados.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Movimentacao> listamov = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_movimentacao where data_saida is null");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Movimentacao mov = new Movimentacao();
                mov.setId(rs.getInt("id"));
                mov.setPlaca(rs.getString("placa"));
                mov.setModelo(rs.getString("modelo"));
                mov.setData_entrada(rs.getTimestamp("data_entrada"));
                mov.setData_saida(rs.getTimestamp("data_saida"));
                mov.setTempo(rs.getInt("tempo"));
                mov.setValor_pago(rs.getFloat("valor_pago"));

                listamov.add(mov);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDados.closeConnection(con, stmt, rs);
        }
        return listamov;
    }

    public List<Movimentacao> read2() {

        Connection con = ConnectionDados.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Movimentacao> listamov = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_movimentacao where data_saida is not null");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Movimentacao mov = new Movimentacao();
                mov.setId(rs.getInt("id"));
                mov.setPlaca(rs.getString("placa"));
                mov.setModelo(rs.getString("modelo"));
                mov.setData_entrada(rs.getTimestamp("data_entrada"));
                mov.setData_saida(rs.getTimestamp("data_saida"));
                mov.setTempo(rs.getInt("tempo"));
                mov.setValor_pago(rs.getFloat("valor_pago"));

                listamov.add(mov);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDados.closeConnection(con, stmt, rs);
        }
        return listamov;
    }
}
