package com.hdh.rendererAndEditor;

import com.hdh.page.WindowInitial;
import org.jvnet.substance.SubstanceButtonUI;
import org.jvnet.substance.button.StandardButtonShaper;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * @ClassName ButtonRenderer
 * @Description TODO
 * @Author Kikityer
 * @Date 2019/3/21 14:51
 * @Version 1.0.0
 **/
public class ButtonRenderer implements TableCellRenderer {

    /**
     * 单元格的按钮渲染
     */
    JPanel panel;
    JButton button;

    public ButtonRenderer() {
        this.initButton();
        this.initPanel();
        // 添加按钮。
        this.panel.add(this.button);
    }
    private void initButton()
    {
        this.button = new JButton();
        // 设置按钮的大小及位置。
        float a = (WindowInitial.dataPanelWidth)/14;
        float b = (float) ((WindowInitial.dataPanelHeight*0.8-50)/4);
        this.button.setBounds((int) (a*0.1), (int) (b*0.35), (int) (a*0.8), (int) (b*0.3));
    }

    private void initPanel()
    {
        this.panel = new JPanel();
        // panel使用绝对定位，这样button就不会充满整个单元格。
        this.panel.setLayout(null);
        this.panel.setBackground(new Color(176,226,255));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.button.setForeground(Color.black);
        this.button.setBackground(new Color(39,64,139));
        this.button.setFont(new Font("Microsoft Yahei",Font.PLAIN,15));

        if (column == 4){
            this.button.setText("<html>选择<br>文件</html>");
        }else {
            this.button.setText((value == null) ? "" : value.toString());
        }

        // 情况一：启用/禁用按钮
        if (column == 1 || column == 2 || column == 3 || column == 4){
            this.button.setEnabled(false);
        }
        return this.panel;
    }
}
