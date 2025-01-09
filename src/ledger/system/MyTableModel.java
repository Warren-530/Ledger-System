/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Liyik
 */
class MyTableModel extends AbstractTableModel{
private Object[][]data;
private String [] columnNames;
    public MyTableModel(Object[][]d,String[]cn){
        data=d;
        columnNames=cn;
    }
@Override
    public boolean isCellEditable(int row,int col){
        return false;
    }
@Override
    public Object getValueAt(int row, int col){
        return data[row][col];
    }
@Override
    public int getColumnCount(){
        return columnNames.length;
    }
@Override
    public int getRowCount(){
        return data.length;
    }
@Override
    public String getColumnName(int col){
        return columnNames[col];
    }
}
