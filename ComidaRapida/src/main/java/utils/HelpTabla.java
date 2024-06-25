package utils;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


public class HelpTabla {
    
    //Ajusta el ancho de las columnas al contenido de cada celda
    public static void ajustarAnchoColumnas(JTable table) {
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            //int preferredWidth = tableColumn.getMinWidth();
            int preferredWidth = 100;
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                // Si el ancho preferido supera el máximo permitido, se establece al máximo permitido
                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth(preferredWidth);
        }
    }
    
    //Método para ocultar columnas de la tabla
    public static void ocultarColumna(JTable table, int columna) {
        table.getColumnModel().getColumn(columna).setMaxWidth(0);
        table.getColumnModel().getColumn(columna).setMinWidth(0);
        table.getColumnModel().getColumn(columna).setPreferredWidth(0);
    }
}
