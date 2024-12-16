/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Liyik
 */
public class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
    private final List< List< Integer > > rowAndCellHeightList = new ArrayList<>();
  @Override public void updateUI() {
    super.updateUI();
    setLineWrap(true);
    setWrapStyleWord(true);
    setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    setName("Table.cellRenderer");
  }
  @Override public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus,
      int row, int column) {
    setFont(table.getFont());
    setText(Objects.toString(value, ""));
    adjustRowHeight(table, row, column);
    return this;
  }


  private void adjustRowHeight(JTable table, int row, int column) {

    setBounds(table.getCellRect(row, column, false));
    // doLayout();

    int preferredHeight = getPreferredSize().height;
    while (rowAndCellHeightList.size() <= row) {
      rowAndCellHeightList.add(new ArrayList<>(column));
    }
    List<Integer> cellHeightList = rowAndCellHeightList.get(row);
    while (cellHeightList.size() <= column) {
      cellHeightList.add(0);
    }
    cellHeightList.set(column, preferredHeight);
    int max = cellHeightList.stream().max(Integer::compare).orElse(0);
    if (table.getRowHeight(row) != max) {
      table.setRowHeight(row, max);
    }
  }
}
