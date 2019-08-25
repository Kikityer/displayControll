package com.hdh.modelPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName ReceiveDataPanel
 * @Description TODO   当点击接收模式下启动按钮则将本模态框弹出，用于显示接收的数据
 * @Author Kikityer
 * @Date 2019/3/21 14:33
 * @Version 1.0.0
 **/
public class ReceiveDataPanel extends JDialog {
    JPanel jPanel; //装载文本域
    public static JTextArea jTextArea;
    JScrollPane jScrollPane;
    public ReceiveDataPanel(JFrame owner){

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(Color.white);

        jTextArea = new JTextArea();
        jTextArea.setEnabled(false);
        jTextArea.setFont(new Font("Microsoft Yahei", Font.PLAIN, 15));
        jTextArea.setTabSize(4);
        jTextArea.setLineWrap(true);// 激活自动换行功能
        jTextArea.setWrapStyleWord(true);// 激活断行不断字功能
        jTextArea.setForeground(Color.white);
        jTextArea.setOpaque(false);

        jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setBorder(BorderFactory.createEtchedBorder());
        jScrollPane.setOpaque(true);

        this.add(jPanel);
        jPanel.add(jScrollPane);
    }
}
