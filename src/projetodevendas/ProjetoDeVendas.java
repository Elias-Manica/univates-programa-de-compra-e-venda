/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetodevendas;

import java.sql.*;
import javax.swing.JOptionPane;
import telas.FramePrincipal;

/**
 *
 * @author Elias
 */
public class ProjetoDeVendas {
    
    static Connection conexao = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(ConectarComOBanco()) {
            new FramePrincipal().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
        
    }
    
    private static boolean ConectarComOBanco() {
        try {
            String dbdriver = "org.postgresql.Driver";
            String dburl = "jdbc:postgresql://localhost:5432/projeto-de-vendas";
            String dbuser = "postgres";
            String dbsenha = "postgres";

            Class.forName(dbdriver);

            if (dbuser.length() != 0) 
            {
                conexao = DriverManager.getConnection(dburl, dbuser, dbsenha);
            } else 
            {
                conexao = DriverManager.getConnection(dburl);
            }

            return true;

        } catch (Exception e) {
            System.err.println("Erro ao tentar conectar no banco: " + e);
            return false;
        }
    }
}
