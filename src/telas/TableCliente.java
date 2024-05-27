/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telas;

/**
 *
 * @author Elias
 */
import entidades.Cliente;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableCliente extends AbstractTableModel {
    private List<Cliente> clientes;
    private String[] colunas = {"Id", "Nome", "Email", "Cpf", "Telefone"};

    public TableCliente(List<Cliente> cliente) {
        this.clientes = cliente;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cliente.getId();
            case 1:
                return cliente.getNome();
            case 2:
                return cliente.getEmail();
            case 3:
                return cliente.getCpf();
            case 4:
                return cliente.getTelefone();
            default:
                return null;
        }
    }
}
