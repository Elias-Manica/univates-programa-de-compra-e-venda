/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetodevendas;

import java.sql.*;
import javax.swing.JOptionPane;
import telas.FramePrincipal;
import apoio.ConexaoBD;
import telas.LoginPage;

/**
 *
 * @author Elias
 */
public class ProjetoDeVendas {
    
    static Connection conexao = null;

    public static void main(String[] args) {
        if(ConexaoBD.getInstance().getConnection() != null) {
            new LoginPage(null, true).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
        
    }
}
