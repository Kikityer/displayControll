package com.hdh.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @ClassName ColumnColor
 * @Description TODO
 * @Author Kikityer
 * @Date 2019/3/21 19:16
 * @Version 1.0.0
 **/
public class ColumnColor {

    public static void setColumnColor(JTable table) {
        try
        {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){
                private static final long serialVersionUID = 1L;
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                    if(row % 2 == 0)
                        setBackground(Color.WHITE);//设置奇数行底色
                    else if(row % 2 == 1)
                        setBackground(new Color(220,230,241));//设置偶数行底色
                    return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
                }
            };
            for(int i = 0; i < table.getColumnCount(); i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
            }
            tcr.setHorizontalAlignment(JLabel.CENTER);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
