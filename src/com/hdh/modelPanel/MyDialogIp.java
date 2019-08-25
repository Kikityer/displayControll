package com.hdh.modelPanel;

import com.hdh.page.DataPanel;
import com.hdh.util.CreateAndSaveLog;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.InetAddress;

/**
 * @ClassName MyDialogIp
 * @Description TODO  设置ip和端口模态框  （可保留上次设置，在下一次打开时显示）
 * @Author Kikityer
 * @Date 2019/3/21 14:29
 * @Version 1.0.0
 **/
public class MyDialogIp extends JDialog implements ActionListener,MouseListener {

    JPanel jPanel;  //最底层容器
    JPanel jPanel1; //装载IP栏
    JPanel jPanel1_1,jPanel1_2,jPanel1_3,jPanel1_4,jPanel1_5,jPanel1_6;  //ip栏的六个IP设置模块
    JPanel jPanel1_1_1,jPanel1_1_2,jPanel1_2_1,jPanel1_2_2,jPanel1_3_1,jPanel1_3_2,jPanel1_4_1,jPanel1_4_2,jPanel1_5_1,jPanel1_5_2,jPanel1_6_1,jPanel1_6_2;
    JPanel jPanel2; //装载按钮
    JButton jButton;

    public static  JTextField jTextField1,jTextField2,jTextField3,jTextField4,jTextField5,jTextField6,jTextField7,jTextField8,jTextField9,jTextField10,jTextField11,jTextField12;
    public MyDialogIp(JFrame owner){

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(6,1,0,5));
        jPanel1.setBackground(new Color(0,154 ,205));
        jPanel2 = new JPanel();
        jPanel2.setBackground(new Color(126,192,238));
        jPanel2.setPreferredSize(new Dimension(450,35));
//        jPanel2.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.white));

        jPanel1_1 = new JPanel();
        jPanel1_1.setBackground(new Color(135,206,255));
        Border b1 = BorderFactory.createEtchedBorder();
        Border b2 = BorderFactory.createTitledBorder("信号处理服务器1");
        jPanel1_1.setBorder(BorderFactory.createCompoundBorder(b1, b2));
        jPanel1_2 = new JPanel();
        jPanel1_2.setBackground(new Color(135,206,255));
        Border b3 = BorderFactory.createTitledBorder("信号处理服务器2");
        jPanel1_2.setBorder(BorderFactory.createCompoundBorder(b1, b3));
        jPanel1_3 = new JPanel();
        jPanel1_3.setBackground(new Color(135,206,255));
        Border b4 = BorderFactory.createTitledBorder("信号处理服务器3");
        jPanel1_3.setBorder(BorderFactory.createCompoundBorder(b1, b4));
        jPanel1_4 = new JPanel();
        jPanel1_4.setBackground(new Color(135,206,255));
        Border b5 = BorderFactory.createTitledBorder("信号处理服务器4");
        jPanel1_4.setBorder(BorderFactory.createCompoundBorder(b1, b5));
        jPanel1_5 = new JPanel();
        jPanel1_5.setBackground(new Color(135,206,255));
        Border b6 = BorderFactory.createTitledBorder("波束调度服务器");
        jPanel1_5.setBorder(BorderFactory.createCompoundBorder(b1, b6));
        jPanel1_6 = new JPanel();
        jPanel1_6.setBackground(new Color(135,206,255));
        Border b7 = BorderFactory.createTitledBorder("本机");
        jPanel1_6.setBorder(BorderFactory.createCompoundBorder(b1, b7));
        jButton = new JButton("保存");
        Font f = new Font("Microsoft Yahei",Font.BOLD,14);
        jButton.setFont(f);
        jButton.setPreferredSize(new Dimension(80,25));
        jButton.addActionListener(this);
        jButton.addMouseListener(this);
        jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        jPanel1_1.setLayout(new GridLayout(2,1,0,1));  //容纳信号处理服务器1的ip和端口
        jPanel1_1_1 = new JPanel();
        jPanel1_1_1.setBackground(new Color(24,116,205));
        jPanel1_1_1.setOpaque(false);
        jPanel1_1_2 = new JPanel();
        jPanel1_1_2.setBackground(new Color(24,116,205));
        jPanel1_1_2.setOpaque(false);
        JLabel jLabel1 = new JLabel("IP    ");
        jLabel1.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField1 = new JTextField(18);
        jTextField1.setHorizontalAlignment(JTextField.CENTER );
        jTextField1.setBackground(Color.white);
        jTextField1.addMouseListener(this);  //绑定鼠标事件
        JLabel jLabel2 = new JLabel("端口");
        jLabel2.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField2 = new JTextField(18);
        jTextField2.setBackground(Color.white);
        jTextField2.setHorizontalAlignment(JTextField.CENTER );
        jTextField2.addMouseListener(this);  //绑定鼠标事件
        jPanel1_1_1.add(jLabel1);
        jPanel1_1_1.add(jTextField1);
        jPanel1_1_2.add(jLabel2);
        jPanel1_1_2.add(jTextField2);

        jPanel1_2.setLayout(new GridLayout(2,1,0,1));  //容纳信号处理服务器2的ip和端口
        jPanel1_2_1 = new JPanel();
        jPanel1_2_1.setBackground(new Color(24,116,205));
        jPanel1_2_1.setOpaque(false);
        jPanel1_2_2 = new JPanel();
        jPanel1_2_2.setBackground(new Color(24,116,205));
        jPanel1_2_2.setOpaque(false);
        JLabel jLabel3 = new JLabel("IP    ");
        jLabel3.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField3 = new JTextField(18);
        jTextField3.setHorizontalAlignment(JTextField.CENTER );
        jTextField3.setBackground(Color.white);
        jTextField3.addMouseListener(this);  //绑定鼠标事件
        JLabel jLabel4 = new JLabel("端口");
        jLabel4.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField4 = new JTextField(18);
        jTextField4.setHorizontalAlignment(JTextField.CENTER );
        jTextField4.setBackground(Color.white);
        jTextField4.addMouseListener(this);  //绑定鼠标事件
        jPanel1_2_1.add(jLabel3);
        jPanel1_2_1.add(jTextField3);
        jPanel1_2_2.add(jLabel4);
        jPanel1_2_2.add(jTextField4);


        jPanel1_3.setLayout(new GridLayout(2,1,0,1));  //容纳信号处理服务器3的ip和端口
        jPanel1_3_1 = new JPanel();
        jPanel1_3_1.setBackground(new Color(24,116,205));
        jPanel1_3_1.setOpaque(false);
        jPanel1_3_2 = new JPanel();
        jPanel1_3_2.setBackground(new Color(24,116,205));
        jPanel1_3_2.setOpaque(false);
        JLabel jLabel5 = new JLabel("IP    ");
        jLabel5.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField5 = new JTextField(18);
        jTextField5.setHorizontalAlignment(JTextField.CENTER );
        jTextField5.setBackground(Color.white);
        jTextField5.addMouseListener(this);  //绑定鼠标事件
        JLabel jLabel6 = new JLabel("端口");
        jLabel6.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField6 = new JTextField(18);
        jTextField6.setHorizontalAlignment(JTextField.CENTER );
        jTextField6.setBackground(Color.white);
        jTextField6.addMouseListener(this);  //绑定鼠标事件
        jPanel1_3_1.add(jLabel5);
        jPanel1_3_1.add(jTextField5);
        jPanel1_3_2.add(jLabel6);
        jPanel1_3_2.add(jTextField6);

        jPanel1_4.setLayout(new GridLayout(2,1,0,1));  //容纳信号处理服务器4的ip和端口
        jPanel1_4_1 = new JPanel();
        jPanel1_4_1.setBackground(new Color(24,116,205));
        jPanel1_4_1.setOpaque(false);
        jPanel1_4_2 = new JPanel();
        jPanel1_4_2.setBackground(new Color(24,116,205));
        jPanel1_4_2.setOpaque(false);
        JLabel jLabel7 = new JLabel("IP    ");
        jLabel7.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField7 = new JTextField(18);
        jTextField7.setHorizontalAlignment(JTextField.CENTER );
        jTextField7.setBackground(Color.white);
        jTextField7.addMouseListener(this);  //绑定鼠标事件
        JLabel jLabel8 = new JLabel("端口");
        jLabel8.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField8 = new JTextField(18);
        jTextField8.setHorizontalAlignment(JTextField.CENTER );
        jTextField8.setBackground(Color.white);
        jTextField8.addMouseListener(this);  //绑定鼠标事件
        jPanel1_4_1.add(jLabel7);
        jPanel1_4_1.add(jTextField7);
        jPanel1_4_2.add(jLabel8);
        jPanel1_4_2.add(jTextField8);

        jPanel1_5.setLayout(new GridLayout(2,1,0,1));  //容纳波束处理服务器的ip和端口
        jPanel1_5_1 = new JPanel();
        jPanel1_5_1.setBackground(new Color(24,116,205));
        jPanel1_5_1.setOpaque(false);
        jPanel1_5_2 = new JPanel();
        jPanel1_5_2.setBackground(new Color(24,116,205));
        jPanel1_5_2.setOpaque(false);
        JLabel jLabel9 = new JLabel("IP    ");
        jLabel9.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField9 = new JTextField(18);
        jTextField9.setHorizontalAlignment(JTextField.CENTER );
        jTextField9.setBackground(Color.white);
        jTextField9.addMouseListener(this);  //绑定鼠标事件
        JLabel jLabel10 = new JLabel("端口");
        jLabel10.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField10 = new JTextField(18);
        jTextField10.setHorizontalAlignment(JTextField.CENTER );
        jTextField10.setBackground(Color.white);
        jTextField10.addMouseListener(this);  //绑定鼠标事件
        jPanel1_5_1.add(jLabel9);
        jPanel1_5_1.add(jTextField9);
        jPanel1_5_2.add(jLabel10);
        jPanel1_5_2.add(jTextField10);

        jPanel1_6.setLayout(new GridLayout(2,1,0,1));  //本机ip和端口
        jPanel1_6_1 = new JPanel();
        jPanel1_6_1.setBackground(new Color(24,116,205));
        jPanel1_6_1.setOpaque(false);
        jPanel1_6_2 = new JPanel();
        jPanel1_6_2.setBackground(new Color(24,116,205));
        jPanel1_6_2.setOpaque(false);
        JLabel jLabel11 = new JLabel("IP    ");
        jLabel11.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField11 = new JTextField(18);
        jTextField11.setHorizontalAlignment(JTextField.CENTER );
        jTextField11.setBackground(Color.white);
        InetAddress ia = null;
        String localip = null;
        try {
            ia = ia.getLocalHost();
            localip = ia.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jTextField11.setText(localip);
        jTextField11.setEditable(false);
        JLabel jLabel12 = new JLabel("端口");
        jLabel12.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        jTextField12 = new JTextField(18);
        jTextField12.setHorizontalAlignment(JTextField.CENTER );
        jTextField12.setBackground(Color.white);
        jTextField12.setText("8888");
        jTextField12.setEditable(false);
        jPanel1_6_1.add(jLabel11);
        jPanel1_6_1.add(jTextField11);
        jPanel1_6_2.add(jLabel12);
        jPanel1_6_2.add(jTextField12);

        this.add(jPanel);
        jPanel.add(jPanel1,BorderLayout.CENTER);
        jPanel.add(jPanel2,BorderLayout.SOUTH);
        jPanel1.add(jPanel1_1);
        jPanel1.add(jPanel1_2);
        jPanel1.add(jPanel1_3);
        jPanel1.add(jPanel1_4);
        jPanel1.add(jPanel1_5);
        jPanel1.add(jPanel1_6);
        jPanel2.add(jButton);

        jPanel1_1.add(jPanel1_1_1);
        jPanel1_1.add(jPanel1_1_2);
        jPanel1_2.add(jPanel1_2_1);
        jPanel1_2.add(jPanel1_2_2);
        jPanel1_3.add(jPanel1_3_1);
        jPanel1_3.add(jPanel1_3_2);
        jPanel1_4.add(jPanel1_4_1);
        jPanel1_4.add(jPanel1_4_2);
        jPanel1_5.add(jPanel1_5_1);
        jPanel1_5.add(jPanel1_5_2);
        jPanel1_6.add(jPanel1_6_1);
        jPanel1_6.add(jPanel1_6_2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton){     //如果填写iP或者端口的文本框中存在没有填写的情况  弹出提示窗口
            if (jTextField1.getText().equals("") || jTextField2.getText().equals("") || jTextField3.getText().equals("")
                    || jTextField4.getText().equals("") || jTextField5.getText().equals("") || jTextField6.getText().equals("")
                    || jTextField7.getText().equals("") || jTextField8.getText().equals("") || jTextField9.getText().equals("")
                    || jTextField10.getText().equals("") || jTextField11.getText().equals("") || jTextField12.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "请填写所有服务器的IP和端口后保存！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
            }else{  //所有的文本框都已经填写，获取文本框的信息之后保存到本地文件中，以便下次读取IP和端口的信息

                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(jTextField1.getText().trim()+"&"+jTextField2.getText().trim());
                stringBuffer.append("&"+jTextField3.getText().trim()+"&"+jTextField4.getText().trim());
                stringBuffer.append("&"+jTextField5.getText().trim()+"&"+jTextField6.getText().trim());
                stringBuffer.append("&"+jTextField7.getText().trim()+"&"+jTextField8.getText().trim());
                stringBuffer.append("&"+jTextField9.getText().trim()+"&"+jTextField10.getText().trim());
                byte[] bytes = stringBuffer.toString().getBytes();
                String dir = System.getProperty("user.dir");
                String path = dir+"\\"+"ipCache.txt";
                File file = new File(path);
                File fileParent = file.getParentFile();
                if (!fileParent.exists()){
                    fileParent.mkdirs();
                }
                try {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);  //保存的IP和端口的txt文件会自动覆盖
                    fos.write(bytes);
                    fos.flush();
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                //在每次更改ip的时候，同时将更改的数据赋值到DataPanel界面的ip和端口容器中*******重要
                if (file.exists()){
                    FileInputStream in = null;  //打开设置ip模态框显示上一次保存数据
                    try {
                        in = new FileInputStream(System.getProperty("user.dir")+"\\"+"ipCache.txt");
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    byte[] bytes1 = new byte[2048];
                    int v = -1;
                    String str = null;
                    try{
                        while ((v = in.read(bytes1,0,bytes1.length)) != -1){
                            str = new String(bytes1,0,v);
                        }
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    //将读取到IP和端口文件填写到模态框中
                    String[] sArray = str.split("&");
                    DataPanel.sever1Ip = sArray[0];
                    DataPanel.sever1Port = sArray[1];
                    DataPanel.sever2Ip = sArray[2];
                    DataPanel.sever2Port = sArray[3];
                    DataPanel.sever3Ip = sArray[4];
                    DataPanel.sever3Port = sArray[5];
                    DataPanel.sever4Ip = sArray[6];
                    DataPanel.sever4Port = sArray[7];
                    DataPanel.severWaveIp = sArray[8];
                    DataPanel.severWavePort = sArray[9];
                }

                CreateAndSaveLog.createSave("操作","更改并保存服务器的地址和端口信息");
                setVisible(false);
            }
        }
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

        if (e.getSource() == jTextField1){
            jTextField1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24,116,205)));
        }else if (e.getSource() == jTextField2){
            jTextField2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24,116,205)));
        }else if (e.getSource() == jTextField3){
            jTextField3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24,116,205)));
        }else if (e.getSource() == jTextField4){
            jTextField4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24,116,205)));
        }else if (e.getSource() == jTextField5){
            jTextField5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24,116,205)));
        }else if (e.getSource() == jTextField6){
            jTextField6.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,new Color(24,116,205)));
        }else if (e.getSource() == jTextField7){
            jTextField7.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24,116,205)));
        }else if (e.getSource() == jTextField8){
            jTextField8.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24,116,205)));
        }else if (e.getSource() == jTextField9){
            jTextField9.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24,116,205)));
        }else if (e.getSource() == jTextField10){
            jTextField10.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24,116,205)));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == jTextField1){
            jTextField1.setBorder(null);
        }else if (e.getSource() == jTextField2){
            jTextField2.setBorder(null);
        }else if (e.getSource() == jTextField3){
            jTextField3.setBorder(null);
        }else if (e.getSource() == jTextField4){
            jTextField4.setBorder(null);
        }else if (e.getSource() == jTextField5){
            jTextField5.setBorder(null);
        }else if (e.getSource() == jTextField6){
            jTextField6.setBorder(null);
        }else if (e.getSource() == jTextField7){
            jTextField7.setBorder(null);
        }else if (e.getSource() == jTextField8){
            jTextField8.setBorder(null);
        }else if (e.getSource() == jTextField9){
            jTextField9.setBorder(null);
        }else if (e.getSource() == jTextField10){
            jTextField10.setBorder(null);
        }
    }
}
