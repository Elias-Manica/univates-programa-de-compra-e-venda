/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidades.Cliente;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Elias
 */
public class ClienteDAO implements IDAOT<Cliente>{

    @Override
    public String salvar(Cliente o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "insert into cliente " + "values" + "(default, " + "'" + o.getNome()+ "', " + "'" + o.getEmail()+ "', " + "'" + o.getCpf()+ "', " + "'" + o.getTelefone()+ "');";
            
            System.err.println("Sql: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        } catch(Exception e) {
            System.out.println("Erro ao inserir o cliente" + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Cliente o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Cliente> consultarTodos() {
         ArrayList<Cliente> clientes = new ArrayList();
        
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "SELECT * FROM cliente;";
            
            System.err.println("Sql: " + sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while(retorno.next()) {
                Cliente cliente = new Cliente();
                
                cliente.setId(retorno.getInt("id"));
                cliente.setNome(retorno.getString("nome"));
                cliente.setEmail(retorno.getString("e_mail"));
                cliente.setCpf(retorno.getString("cpf"));
                cliente.setTelefone(retorno.getString("telefone"));
                
                clientes.add(cliente);
            }
        } catch(Exception e) {
            System.out.println("Erro ao consultar os clientes" + e);
        } 
        
        return clientes;
    }

    @Override
    public ArrayList<Cliente> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cliente consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
