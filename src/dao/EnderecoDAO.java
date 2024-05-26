/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidades.Endereco;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Elias
 */
public class EnderecoDAO implements IDAOT<Endereco> {

    @Override
    public String salvar(Endereco o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "insert into endereco " + "values" + "(default, " + "'" + o.getDescricao() + "', " + "'" + o.getCep() + "');";
            
            System.err.println("Sql: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        } catch(Exception e) {
            System.out.println("Erro ao inserir o endereço" + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Endereco o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Endereco> consultarTodos() {
        ArrayList<Endereco> enderecos = new ArrayList();
        
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "SELECT * FROM endereco;";
            
            System.err.println("Sql: " + sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while(retorno.next()) {
                Endereco endereco = new Endereco();
                
                endereco.setId(retorno.getInt("id"));
                endereco.setCep(retorno.getString("cep"));
                endereco.setDescricao(retorno.getString("descricao"));
                
                enderecos.add(endereco);
            }
        } catch(Exception e) {
            System.out.println("Erro ao consultar os endereços" + e);
        } 
        
        return enderecos;
    }

    @Override
    public ArrayList<Endereco> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Endereco consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
