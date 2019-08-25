package com.hdh.modelPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @ClassName LaunchParaPanel
 * @Description TODO  发射参数模态框
 * @Author Kikityer
 * @Date 2019/3/21 14:28
 * @Version 1.0.0
 **/
public class LaunchParaPanel extends JPanel implements MouseListener {

    JPanel jPanel1_2_1_2;

    //发射界面组件（初始化界面）
    JLabel jLabel5,jLabel6,jLabel7,jLabel8,jLabel9,jLabel10,jLabel11,jLabel12,jLabel13; //发射界面（初始化界面）
    public static JComboBox jComboBox1;
    public static JComboBox jComboBox2;
    public static JComboBox jComboBox3;
    public static JTextField jtf1;
    public static JTextField jtf2;
    public static JTextField jtf3;
    public static JTextField jtf4;
    public static JTextField jtf5;
    public static JTextField jtf6;

    LaunchParaPanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0, 191, 255));

        jPanel1_2_1_2 = new JPanel();  //放置 具体的参数设置
        jPanel1_2_1_2.setLayout(new GridLayout(10,2,1,10));
        jPanel1_2_1_2.setBackground(new Color(99,184,255));
        jPanel1_2_1_2.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4,  new Color(99,184,255)));
        jLabel5 = new JLabel("调制类型",JLabel.CENTER);
        jLabel5.setFont(new Font("Microsoft Yahei",Font.PLAIN,12));
        jComboBox1 = new JComboBox<String>();
        jComboBox1.setBackground(new Color(135,206,255));
        jComboBox1.setPreferredSize(new Dimension(30,10));
        jComboBox1.setEditable(false);
        jComboBox1.setEnabled(true);
        jComboBox1.addItem("BPSK");
        jComboBox1.addItem("扩频");
        jComboBox1.setSelectedItem(null);
        jComboBox1.addMouseListener(this);  //添加鼠标事件
        jLabel6 = new JLabel("M序列1生成多项式",JLabel.CENTER);
        jLabel6.setFont(new Font("Microsoft Yahei",Font.PLAIN,12));
        jtf1 = new JTextField();
        jtf1.addMouseListener(this);  //添加鼠标事件
        jtf1.setBackground(new Color(135,206,255));
        jLabel7 = new JLabel("M序列1初始状态",JLabel.CENTER);
        jLabel7.setFont(new Font("",Font.PLAIN,12));
        jtf2 = new JTextField();
        jtf2.addMouseListener(this);  //添加鼠标事件
        jtf2.setBackground(new Color(135,206,255));
        jLabel8 = new JLabel("M序列2生成多项式",JLabel.CENTER);
        jLabel8.setFont(new Font("Microsoft Yahei",Font.PLAIN,12));
        jtf3 = new JTextField();
        jtf3.addMouseListener(this);  //添加鼠标事件
        jtf3.setBackground(new Color(135,206,255));
        jLabel9 = new JLabel("M序列2初始状态",JLabel.CENTER);
        jLabel9.setFont(new Font("Microsoft Yahei",Font.PLAIN,12));
        jtf4 = new JTextField();
        jtf4.addMouseListener(this);  //添加鼠标事件
        jtf4.setBackground(new Color(135,206,255));
        jLabel10 = new JLabel("纠错码类型",JLabel.CENTER);
        jLabel10.setFont(new Font("Microsoft Yahei",Font.PLAIN,12));
        jComboBox2 = new JComboBox<String>();
        jComboBox2.setEditable(false);
        jComboBox2.setEnabled(true);
        jComboBox2.addItem("无编码");
        jComboBox2.addItem("卷积+RS码");
        jComboBox2.addItem("LDPC码");
        jComboBox2.setSelectedItem(null);
        jComboBox2.addMouseListener(this);  //添加鼠标事件
        jLabel11 = new JLabel("数据速率",JLabel.CENTER);
        jLabel11.setFont(new Font("Microsoft Yahei",Font.PLAIN,12));
        jtf5 = new JTextField();
        jtf5.addMouseListener(this);  //添加鼠标事件
        jtf5.setBackground(new Color(135,206,255));
        jLabel12 = new JLabel("滚降系数",JLabel.CENTER);
        jLabel12.setFont(new Font("Microsoft Yahei",Font.PLAIN,12));
        jComboBox3 = new JComboBox<String>();
        jComboBox3.setEditable(false);
        jComboBox3.setEnabled(true);
        jComboBox3.addItem("0.35");
        jComboBox3.addItem("0.6");
        jComboBox3.addItem("1");
        jComboBox3.setSelectedItem(null);
        jComboBox3.addMouseListener(this);  //添加鼠标事件
        jLabel13 = new JLabel("发射通信时隙长度",JLabel.CENTER);
        jLabel13.setFont(new Font("Microsoft Yahei",Font.PLAIN,12));
        jtf6 = new JTextField();
        jtf6.setBackground(new Color(135,206,255));
        jtf6.addMouseListener(this);  //添加鼠标事件

        this.add(jPanel1_2_1_2);
        jPanel1_2_1_2.add(jLabel5);
        jPanel1_2_1_2.add(jComboBox1);
        jPanel1_2_1_2.add(jLabel6);
        jPanel1_2_1_2.add(jtf1);
        jPanel1_2_1_2.add(jLabel7);
        jPanel1_2_1_2.add(jtf2);
        jPanel1_2_1_2.add(jLabel8);
        jPanel1_2_1_2.add(jtf3);
        jPanel1_2_1_2.add(jLabel9);
        jPanel1_2_1_2.add(jtf4);
        jPanel1_2_1_2.add(jLabel10);
        jPanel1_2_1_2.add(jComboBox2);
        jPanel1_2_1_2.add(jLabel11);
        jPanel1_2_1_2.add(jtf5);
        jPanel1_2_1_2.add(jLabel12);
        jPanel1_2_1_2.add(jComboBox3);
        jPanel1_2_1_2.add(jLabel13);
        jPanel1_2_1_2.add(jtf6);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == jComboBox1){
            jComboBox1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }else if (e.getSource() == jtf1){
            jtf1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }else if (e.getSource() == jtf2){
            jtf2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }else if (e.getSource() == jtf3){
            jtf3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }else if (e.getSource() == jtf4){
            jtf4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }else if (e.getSource() == jComboBox2){
            jComboBox2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }else if (e.getSource() == jtf5){
            jtf5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }else if (e.getSource() == jComboBox3){
            jComboBox3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }else if (e.getSource() == jtf6){
            jtf6.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getSource() == jComboBox1){
            jComboBox1.setBorder(null);
        }else if (e.getSource() == jtf1){
            jtf1.setBorder(null);
        }else if (e.getSource() == jtf2){
            jtf2.setBorder(null);
        }else if (e.getSource() == jtf3){
            jtf3.setBorder(null);
        }else if (e.getSource() == jtf4){
            jtf4.setBorder(null);
        }else if (e.getSource() == jComboBox2){
            jComboBox2.setBorder(null);
        }else if (e.getSource() == jtf5){
            jtf5.setBorder(null);
        }else if (e.getSource() == jComboBox3){
            jComboBox3.setBorder(null);
        }else if (e.getSource() == jtf6){
            jtf6.setBorder(null);
        }
    }
}
