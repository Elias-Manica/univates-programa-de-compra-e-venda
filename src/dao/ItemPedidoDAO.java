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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
}
