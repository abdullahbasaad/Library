package bcu.cmp5332.librarysystem.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Changes table format such as cell's background.
 * @author abdjory
 *
 */
@SuppressWarnings("serial")
class ColumnColorRenderer extends DefaultTableCellRenderer {
	   Color backgroundColor, foregroundColor;
	   
	   public ColumnColorRenderer(Color backgroundColor, Color foregroundColor) {
	      super();
	      this.backgroundColor = backgroundColor;
	      this.foregroundColor = foregroundColor;
	   }
	   
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
	      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      cell.setBackground(backgroundColor);
	      cell.setForeground(foregroundColor);
	      return cell;
	   }
}