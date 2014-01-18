/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.visorcliente.table;

import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class DateRenderer extends JLabel
        implements TableCellRenderer {

    public DateRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        setText(format.format(value));
        return this;
    }
}