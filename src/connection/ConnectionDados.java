/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import com.sun.source.tree.IfTree;
import dao.UsuarioDAO;
import dao.ValoresDAO;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Evair Daniel
 */
public class ConnectionDados {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    public static String USER;
    public static String PASS;
    public static String HOST;
    public static String PORTA;
    private static String URL = "jdbc:mysql://" + HOST + ":" + PORTA + "/estacionamento";

    private static Connection CONEXAO;

    public static Connection getConnection() {
        try {
            
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL + "/estacionamento", USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão", ex);
        }

    }

    public static boolean getConnection(String Host, String Porta, String Usuario, String Senha) {
        boolean con = false;
        URL = "jdbc:mysql://" + Host + ":" + Porta + "";
        USER = Usuario;
        PASS = Senha;

        try {

            Class.forName(DRIVER);

            CONEXAO = DriverManager.getConnection(URL, USER, PASS);
            con = true;

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no servidor!");
        }
        return con;
    }

    public static boolean Cria_tabelas() {
        boolean sucesso = false;
        if (Create_Data_Base()) {
            if (Create_Tabela_Usuario()) {
                if (Create_Tabela_Movimentacao()) {
                    if(Create_Tabela_Valor()){
                        UsuarioDAO dao = new UsuarioDAO();
                        if (dao.create()){
                            ValoresDAO vdao = new ValoresDAO();
                            if(vdao.create()){
                             sucesso = true;
                            }                          
                        }
                    }
                }

            }
        }
        return sucesso;

    }

    private static boolean Create_Tabela_Usuario() {
        boolean create = false;
        PreparedStatement stm;
        String sql = "CREATE TABLE IF NOT EXISTS estacionamento.tbl_usuario ("
                + "  id int NOT NULL AUTO_INCREMENT,\n"
                + "  nome char(60) DEFAULT NULL,\n"
                + "  usuario char(20) NOT NULL,\n"
                + "  senha char(6) NOT NULL,\n"
                + "  PRIMARY KEY (id)\n"
                + ") ;";
        try {
            stm = CONEXAO.prepareCall(sql);
            stm.execute();
            create = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Criar o TABLE tbl_usuario");
        }
        return create;
    }

    private static boolean Create_Tabela_Movimentacao() {
        boolean create = false;
        PreparedStatement stm;
        String sql = "CREATE TABLE  IF NOT EXISTS estacionamento.tbl_movimentacao (\n"
                + "  id int NOT NULL AUTO_INCREMENT,\n"
                + "  placa char(7) NOT NULL,\n"
                + "  modelo char(40) NOT NULL,\n"
                + "  data_entrada datetime NOT NULL,\n"
                + "  data_saida datetime DEFAULT NULL,\n"
                + "  tempo int DEFAULT NULL,\n"
                + "  valor_pago decimal(8,3) DEFAULT NULL,\n"
                + "  PRIMARY KEY (id)\n"
                + ");";
        try {
            stm = CONEXAO.prepareCall(sql);
            stm.execute();
            create = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Criar o TABLE tbl_movimentacao ");
        }
        return create;
    }

    private static boolean Create_Tabela_Valor() {
        boolean create = false;
        PreparedStatement stm;
        String sql = "CREATE TABLE  IF NOT EXISTS estacionamento.tbl_valor (\n"
                + "  id int NOT NULL AUTO_INCREMENT,\n"
                + "  valor_primeira_hora decimal(8,3) NOT NULL,\n"
                + "  valor_demais_horas decimal(8,3) NOT NULL,\n"
                + "  data_fim varchar(45) DEFAULT NULL,\n"
                + "  PRIMARY KEY (id)\n"
                + ") ";
        try {
            stm = CONEXAO.prepareCall(sql);
            stm.execute();
            create = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Criar o TABLE tbl_valor");
        }
        return create;
    }

    private static boolean Create_Data_Base() {
        boolean create = false;
        PreparedStatement stm;
        String sql = "CREATE DATABASE IF NOT EXISTS estacionamento";
        try {
            stm = CONEXAO.prepareCall(sql);
            stm.execute();
            create = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Criar o DATABASE");
        }
        return create;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDados.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDados.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
