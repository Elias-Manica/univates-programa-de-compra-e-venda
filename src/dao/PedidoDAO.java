package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAOT;
import entidades.Pedido;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Elias
 */
public class PedidoDAO implements IDAOT<Pedido> {

    @Override
    public String salvar(Pedido o) {
        try {
            String sql = "INSERT INTO pedido (data, endereco_entrega, observacao, cliente_id) " +
                         "VALUES (?, ?, ?, ?) RETURNING id;";
            
            PreparedStatement pst = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            
            String data = o.getData();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date parsed = format.parse(data);
            Date sqlDate = new Date(parsed.getTime());
            
            pst.setDate(1, sqlDate);
            pst.setString(2, o.getEndereco_entrega());
            pst.setString(3, o.getObservacao());
            pst.setInt(4, o.getClient_id());

            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return String.valueOf(rs.getInt(1)); // Retorna o ID gerado como String
            } else {
                return "Erro ao obter ID do pedido";
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir o pedido: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Pedido o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "update pedido set " +
                         "data='" + o.getData() + "', " +
                         "endereco_entrega='" + o.getEndereco_entrega() + "', " +
                         "observacao='" + o.getObservacao() + "', " +
                         "cliente_id='" + o.getClient_id() + "' " +
                         "where id='" + o.getId() + "';";
            
            System.err.println("Sql: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o pedido: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "delete from pedido where id='" + id + "';";
            
            System.err.println("Sql: " + sql);
            
            int retorno = st.executeUpdate(sql);
            
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir o pedido: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<Pedido> consultarTodos() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "SELECT * FROM pedido;";
            
            System.err.println("Sql: " + sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while (retorno.next()) {
                Pedido pedido = new Pedido();
                
                pedido.setId(retorno.getInt("id"));
                pedido.setData(retorno.getString("data"));
                pedido.setEndereco_entrega(retorno.getString("endereco_entrega"));
                pedido.setObservacao(retorno.getString("observacao"));
                pedido.setClient_id(retorno.getInt("cliente_id"));
                
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar os pedidos: " + e);
        }
        
        return pedidos;
    }

    @Override
    public ArrayList<Pedido> consultar(String criterio) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "SELECT * FROM pedido WHERE endereco_entrega ILIKE '%" + criterio + "%';";
            
            System.err.println("Sql: " + sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while (retorno.next()) {
                Pedido pedido = new Pedido();
                
                pedido.setId(retorno.getInt("id"));
                pedido.setData(retorno.getString("data"));
                pedido.setEndereco_entrega(retorno.getString("endereco_entrega"));
                pedido.setObservacao(retorno.getString("observacao"));
                pedido.setClient_id(retorno.getInt("cliente_id"));
                
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar os pedidos: " + e);
        }
        
        return pedidos;
    }

    @Override
    public Pedido consultarId(int id) {
        Pedido pedido = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "SELECT * FROM pedido WHERE id = " + id;
            
            System.err.println("Sql: " + sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while (retorno.next()) {
                pedido = new Pedido();
                
                pedido.setId(retorno.getInt("id"));
                pedido.setData(retorno.getString("data"));
                pedido.setEndereco_entrega(retorno.getString("endereco_entrega"));
                pedido.setObservacao(retorno.getString("observacao"));
                pedido.setClient_id(retorno.getInt("cliente_id"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar o pedido: " + e);
        } 
        return pedido;
    }

    public void popularTabela(JTable tabela, String criterio) {
        
        ResultSet resultadoQ;
        
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Id";
        cabecalho[1] = "Data";
        cabecalho[2] = "Endereço de Entrega";
        cabecalho[3] = "Observação";
        cabecalho[4] = "ID do Cliente";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM pedido "
                    + "WHERE "
                    + "endereco_entrega ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][5];

        } catch (Exception e) {
            System.out.println("Erro ao consultar pedidos: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM pedido "
                    + "WHERE "
                    + "endereco_entrega ILIKE '%" + criterio + "%' ORDER BY id");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("data");
                dadosTabela[lin][2] = resultadoQ.getString("endereco_entrega");
                dadosTabela[lin][3] = resultadoQ.getString("observacao");
                dadosTabela[lin][4] = resultadoQ.getInt("cliente_id");

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
                    return String.class;
                }
                return Object.class;
            }
        });

        // permite selecao de apenas uma linha da tabela
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
                    column.setPreferredWidth(100);
                    break;
                case 2:
                    column.setPreferredWidth(150);
                    break;
                case 3:
                    column.setPreferredWidth(200);
                    break;
                case 4:
                    column.setPreferredWidth(100);
                    break;
            }
        }
    }
}
