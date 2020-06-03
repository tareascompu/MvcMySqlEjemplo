package controlador;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColoresTabla extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.setOpaque(true);

        if (table.getValueAt(row, 4).equals(1)) {
            this.setBackground(Color.BLACK);
            this.setForeground(Color.RED);
        }else if(table.getValueAt(row, 4).equals(4)){
            this.setBackground(Color.RED);
            this.setForeground(Color.BLACK);
        }else if(table.getValueAt(row, 2).toString().equals("Seis")){
            this.setBackground(Color.YELLOW);
            this.setForeground(Color.BLACK);
        }else{
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }
        if(isSelected) {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }
        return this;
    }
}
