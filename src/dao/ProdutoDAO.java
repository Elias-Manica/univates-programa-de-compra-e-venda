/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidades.Produto;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Elias
 */
public class ProdutoDAO implements IDAOT<Produto> {

    @Override
    public String salvar(Produto o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "insert into produto " + "values" + "(default, " + "'" + o.getDescricao()+ "', " + "'" + o.getValor_unitario()+ "', " + "'" + o.getQtde_estoque() +  "');";
            
            System.err.println("Sql: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        } catch(Exception e) {
            System.out.println("Erro ao inserir o produto" + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Produto o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "update produto set descricao='"+ o.getDescricao()+ "', valor_unitario='"+ o.getValor_unitario()+ "', qtde_estoque='"+ o.getQtde_estoque()+ "' where id='"+ o.getId() + "'";
            
            System.err.println("Sql: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        } catch(Exception e) {
            System.out.println("Erro ao atualizar o produto" + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "delete from produto where id='"+ id + "'";
            
            System.err.println("Sql: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        } catch(Exception e) {
            System.out.println("Erro ao excluir o produto" + e);
            return e.toString();
        }}

    @Override
    public ArrayList<Produto> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Produto> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Produto consultarId(int id) {
        Produto produto = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "SELECT * FROM produto WHERE id = " + id;
            
            System.err.println("Sql: " + sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while(retorno.next()) {
                produto = new Produto();
                
                produto.setId(retorno.getInt("id"));
                produto.setDescricao(retorno.getString("descricao"));
                produto.setValor_unitario(retorno.getString("valor_unitario"));
                produto.setQtde_estoque(retorno.getString("qtde_estoque"));
            }
        } catch(Exception e) {
            System.out.println("Erro ao consultar o produto" + e);
        } 
        return produto; 
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        
    ResultSet resultadoQ;
    
    // dados da tabela
    Object[][] dadosTabela = null;

    // cabeçalho da tabela
    Object[] cabecalho = new Object[4];
    cabecalho[0] = "Id";
    cabecalho[1] = "Descrição";
    cabecalho[2] = "Valor Unitário";
    cabecalho[3] = "Quantidade em Estoque";

    // cria matriz de acordo com nº de registros da tabela
    try {
        resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                + "SELECT count(*) "
                + "FROM produto "
                + "WHERE "
                + "descricao ILIKE '%" + criterio + "%'");

        resultadoQ.next();

        dadosTabela = new Object[resultadoQ.getInt(1)][4];

    } catch (Exception e) {
        System.out.println("Erro ao consultar produtos: " + e);
    }

    int lin = 0;

    // efetua consulta na tabela
    try {
        resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                + "SELECT * "
                + "FROM produto "
                + "WHERE "
                + "descricao ILIKE '%" + criterio + "%' ORDER BY id");

        while (resultadoQ.next()) {

            dadosTabela[lin][0] = resultadoQ.getInt("Id");
            dadosTabela[lin][1] = resultadoQ.getString("Descricao");
            dadosTabela[lin][2] = resultadoQ.getString("Valor_Unitario");
            dadosTabela[lin][3] = resultadoQ.getString("Qtde_Estoque");

            lin++;
        }
    } catch (Exception e) {
        System.out.println("Problemas para popular tabela...");
        System.out.println(e);
    }

    // configurações adicionais no componente tabela
    tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
        @Override
        // quando retorno for FALSE, a tabela não é editável
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        // alteração no método que determina a coluna em que o objeto ImageIcon deverá aparecer
        @Override
        public Class getColumnClass(int column) {
            return Object.class;
        }
    });

    // permite seleção de apenas uma linha da tabela
    tabela.setSelectionMode(0);

    // redimensiona as colunas de uma tabela
    TableColumn column = null;
    for (int i = 0; i < tabela.getColumnCount(); i++) {
        column = tabela.getColumnModel().getColumn(i);
        switch (i) {
            case 0:
                column.setPreferredWidth(17);
                break;
            case 1:
                column.setPreferredWidth(140);
                break;
            case 2:
                column.setPreferredWidth(100);
                break;
            case 3:
                column.setPreferredWidth(100);
                break;
        }
    }
}
    
}
