package com.hdh.rendererAndEditor;

import com.hdh.page.StarPicPanel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * @ClassName IconRenderer1_1
 * @Description TODO
 * @Author Kikityer
 * @Date 2019/3/27 9:39
 * @Version 1.0.0
 **/
public class IconRenderer1_1 implements TableCellRenderer {
    JLabel label;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row == 0){
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/hdh/img/wrong.png"));
            label = new JLabel(icon);
            label.setBackground(Color.white);
            label.setOpaque(true);
        }
        if (row == 1){
            if (StarPicPanel.linkFlag2 != 0 || StarPicPanel.conditionFlag2 != 0){
                ImageIcon icon = new ImageIcon(getClass().getResource("/com/hdh/img/right.png"));
                label = new JLabel(icon);
                label.setBackground(Color.white);
                label.setOpaque(true);
            }else {
                ImageIcon icon = new ImageIcon(getClass().getResource("/com/hdh/img/wrong.png"));
                label = new JLabel(icon);
                label.setBackground(Color.white);
                label.setOpaque(true);
            }
        }
        if (row == 2){
            if (StarPicPanel.linkFlag3 != 0 || StarPicPanel.conditionFlag3 != 0){
                ImageIcon icon = new ImageIcon(getClass().getResource("/com/hdh/img/right.png"));
                label = new JLabel(icon);
                label.setBackground(Color.white);
                label.setOpaque(true);
            }else {
                ImageIcon icon = new ImageIcon(getClass().getResource("/com/hdh/img/wrong.png"));
                label = new JLabel(icon);
                label.setBackground(Color.white);
                label.setOpaque(true);
            }
        }
        if (row == 3){
            if (StarPicPanel.linkFlag4 != 0 || StarPicPanel.conditionFlag4 != 0){
                ImageIcon icon = new ImageIcon(getClass().getResource("/com/hdh/img/right.png"));
                label = new JLabel(icon);
                label.setBackground(Color.white);
                label.setOpaque(true);
            }else {
                ImageIcon icon = new ImageIcon(getClass().getResource("/com/hdh/img/wrong.png"));
                label = new JLabel(icon);
                label.setBackground(Color.white);
                label.setOpaque(true);
            }
        }
        return label;
    }
}
