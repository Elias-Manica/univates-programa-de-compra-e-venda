/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telas;

/**
 *
 * @author Elias
 */
import entidades.Endereco;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableEndereco extends AbstractTableModel {
    private List<Endereco> enderecos;
    private String[] colunas = {"Id", "Cep", "Descrição"};

    public TableEndereco(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public int getRowCount() {
        return enderecos.size();
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
        Endereco endereco = enderecos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return endereco.getId();
            case 1:
                return endereco.getCep();
            case 2:
                return endereco.getDescricao();
            default:
                return null;
        }
    }
}
