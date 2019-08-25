package com.hdh.rendererAndEditor;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * @ClassName IconRenderer
 * @Description TODO
 * @Author Kikityer
 * @Date 2019/3/21 14:53
 * @Version 1.0.0
 **/
public class IconRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/hdh/img/wrong.png"));
        JLabel label = new JLabel(icon);
        label.setBackground(new Color(176,226,255));
        label.setOpaque(true);
        return label;
    }
}
