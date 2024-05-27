/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telas;

/**
 *
 * @author Elias
 */
import entidades.Fornecedor;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableFornecedor extends AbstractTableModel {
    private List<Fornecedor> fornecedores;
    private String[] colunas = {"Id", "Nome", "Email", "Telefone", "Cnpj"};

    public TableFornecedor(List<Fornecedor> fornecedor) {
        this.fornecedores = fornecedor;
    }

    @Override
    public int getRowCount() {
        return fornecedores.size();
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
        Fornecedor fornecedor = fornecedores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return fornecedor.getId();
            case 1:
                return fornecedor.getNome();
            case 2:
                return fornecedor.getEmail();
            case 3:
                return fornecedor.getTelefone();
            case 4:
                return fornecedor.getCnpj();
            default:
                return null;
        }
    }
}
