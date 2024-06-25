/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidades.ItemPedido;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Elias
 */
public class ItemPedidoDAO implements IDAOT<ItemPedido> {

    @Override
     public String salvar(ItemPedido o) {
        try {
            String sql = "INSERT INTO item_pedido (produto_id, pedido_id, qtde, valor_item) " +
                         "VALUES (?, ?, ?, ?);";
            
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            pst.setInt(1, o.getProduto_id());
            pst.setInt(2, o.getPedido_id());
            pst.setDouble(3, o.getQtde());
            pst.setDouble(4, Double.parseDouble(o.getValor_item()));

            int retorno = pst.executeUpdate();
            
            return null; // Retorna null em caso de sucesso
        } catch (Exception e) {
            System.out.println("Erro ao inserir o item do pedido: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(ItemPedido o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "delete from item_pedido where pedido_id='" + id + "';";
            
            System.err.println("Sql: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir o item do pedido: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<ItemPedido> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ItemPedido> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItemPedido consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void popularTabela(JTable tabela, int idPedido) {
        Object[][] dadosTabela = null;
        Object[] cabecalho = {"ID Item", "ID Produto", "Descrição", "Quantidade", "Valor Pago"};

        try {
            Connection connection = ConexaoBD.getInstance().getConnection();

            // Consultar os itens do pedido e dados do produto e pedido associados
            String query = "SELECT ip.id AS id_item, ip.produto_id, pr.descricao, ip.qtde, ip.valor_item, "
                         + "p.data, p.endereco_entrega, p.observacao "
                         + "FROM item_pedido ip "
                         + "JOIN produto pr ON ip.produto_id = pr.id "
                         + "JOIN pedido p ON ip.pedido_id = p.id "
                         + "WHERE ip.pedido_id = ?";

            PreparedStatement ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            // Contar o número de itens no pedido específico
            rs.last(); // mover para a última linha
            int numRows = rs.getRow(); // obter número de linhas
            rs.beforeFirst(); // mover de volta para o início

            dadosTabela = new Object[numRows][9]; // alterado para 9 colunas

            int lin = 0;
            while (rs.next()) {
                dadosTabela[lin][0] = rs.getInt("id_item");
                dadosTabela[lin][1] = rs.getInt("produto_id");
                dadosTabela[lin][2] = rs.getString("descricao");
                dadosTabela[lin][3] = rs.getDouble("qtde");
                dadosTabela[lin][4] = rs.getString("valor_item");
                lin++;
            }

            // Fechar PreparedStatement e ResultSet
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("Erro ao consultar itens do pedido: " + e);
        }

        // Criar um modelo de tabela não editável e definir na JTable
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }




}
