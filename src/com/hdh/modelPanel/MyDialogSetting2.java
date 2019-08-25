package com.hdh.modelPanel;

import com.hdh.codeFrame.server2.ConfigFrame1ser2;
import com.hdh.codeFrame.server2.ConfigFrame2ser2;
import com.hdh.page.DataPanel;
import com.hdh.page.StarPicPanel;
import com.hdh.rendererAndEditor.ButtonEditor1;
import com.hdh.rendererAndEditor.ButtonEditor2;
import com.hdh.rendererAndEditor.ButtonRenderer1;
import com.hdh.rendererAndEditor.ButtonRenderer2;
import com.hdh.util.CreateAndSaveLog;
import com.hdh.util.UdpSend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.text.ParseException;

/**
 * @ClassName MyDialogSetting2
 * @Description TODO  服务器2参数配置模态框   （可保留上次设置，在下一次打开时显示）
 * @Author Kikityer
 * @Date 2019/3/21 14:31
 * @Version 1.0.0
 **/
public class MyDialogSetting2 extends JDialog implements ActionListener,MouseListener {

    JPanel jPanel;  //最底层容器
    JPanel jPanel1; //装载任务类型和具体参数
    JPanel jPanel1_1,jPanel1_2;  //分别装载任务类型和具体参数设置
    JPanel jPanel1_2_1;
    JPanel jPanel1_2_1_1;//装载发射和接收参数label按钮
    JPanel jPanel1_1_1; //装载三个单选按钮
    JPanel jPanel2; //装载按钮
    JButton jButton;

    JLabel jLabel1,jLabel2,jLabel3,jLabel4;

    JRadioButton jb1,jb2,jb3;

    //从本都缓存文件中读取到的服务器1的发射文件
    public static String mod_type1;
    public static String m1_gen1;
    public static String m1_init1;
    public static String m2_gen1;
    public static String m2_init1;
    public static String ecc_type1;
    public static String data_rate1;
    public static String rf_fac1;
    public static String slot_time1;

    //从本都缓存文件中读取到的服务器1的接收文件
    public static String mod_type2;
    public static String m1_gen2;
    public static String m1_init2;
    public static String m2_gen2;
    public static String m2_init2;
    public static String ecc_type2;
    public static String data_rate2;
    public static String rf_fac2;
    public static String slot_time2;

    UdpSend udpSend = new UdpSend();
    ConfigFrame1ser2 configFrame1ser2 = new ConfigFrame1ser2();
    ConfigFrame2ser2 configFrame2ser2 = new ConfigFrame2ser2();

    public MyDialogSetting2(JFrame owner) throws IOException {
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.setBackground(new Color(126,192,238));
        jPanel2 = new JPanel();
        jPanel2.setBackground(new Color(126,192,238));
        jPanel2.setPreferredSize(new Dimension(450,35));
        jPanel2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));

        jPanel1_1 = new JPanel();  //任务类型面板
        jPanel1_1.setLayout(new GridLayout(1,2,0,0));
        jPanel1_1.setBackground(new Color(135,206,255));
        jPanel1_1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1_1.setPreferredSize(new Dimension(400,60));
        jPanel1_2 = new JPanel();  //具体参数设置
        jPanel1_2.setLayout(null);
        jPanel1_2.setBackground(new Color(135,206,255));
        jPanel1_2.setBorder(BorderFactory.createEtchedBorder());
        jButton = new JButton("配置");
        Font f = new Font("宋体",Font.BOLD,14);
        jButton.setFont(f);
        jButton.setPreferredSize(new Dimension(80,25));
        jButton.addActionListener(this);
//        jButton.addMouseListener(this);
        jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel jLabel = new JLabel("  任务类型:");
        jPanel1_1_1 = new JPanel();
        jPanel1_1_1.setLayout(new GridLayout(1, 4));// 定义排版，一行四列
        jb1 = new JRadioButton("发射",true);
        jb1.setBackground(new Color(99,184,255));
        jb1.addActionListener(this);
        jb1.addMouseListener(this);
        jb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jb2 = new JRadioButton("接收");
        jb2.setBackground(new Color(135,206,255));
        jb2.addActionListener(this);
        jb2.addMouseListener(this);
        jb2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jb3 = new JRadioButton("收发");
        jb3.setBackground(new Color(135,206,255));
        jb3.addActionListener(this);
        jb3.addMouseListener(this);
        jb3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jPanel1_1_1.add(this.jb1);// 加入组件
        jPanel1_1_1.add(this.jb2);// 加入组件
        jPanel1_1_1.add(this.jb3);// 加入组件
        ButtonGroup group = new ButtonGroup();
        group.add(this.jb1);
        group.add(this.jb2);
        group.add(this.jb3);

        jPanel1_2_1 = new JPanel();  //用于装载参数按钮和具体参数
        jPanel1_2_1.setLayout(new BorderLayout());
        jPanel1_2_1.setBackground(new Color(135,206,255));
        jPanel1_2_1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1_2_1.setBounds(10,10,370,470);

        jPanel1_2_1_1 = new JPanel();  //放置 发射参数和接收参数两个标签
        jPanel1_2_1_1.setLayout(new GridLayout(1,3));
        jPanel1_2_1_1.setPreferredSize(new Dimension(400,30));
        jPanel1_2_1_1.setBackground(new Color(135,206,255));
        jLabel1 = new JLabel("发射参数",JLabel.CENTER);
        jLabel1.setOpaque(true);
        jLabel1.setBackground(new Color(99,184,255));
        jLabel1.setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, new Color(99,184,255)));
        jLabel1.addMouseListener(this);
        jLabel2 = new JLabel("接收参数",JLabel.CENTER);
        jLabel2.addMouseListener(this);
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();


        this.add(jPanel);
        jPanel.add(jPanel1,BorderLayout.CENTER);
        jPanel.add(jPanel2,BorderLayout.SOUTH);
        jPanel1.add(jPanel1_1,BorderLayout.NORTH);
        jPanel1.add(jPanel1_2,BorderLayout.CENTER);
        jPanel2.add(jButton);
        jPanel1_1.add(jLabel);
        jPanel1_1.add(jPanel1_1_1);

        jPanel1_2.add(jPanel1_2_1);
        jPanel1_2_1.add(jPanel1_2_1_1,BorderLayout.NORTH);
        jPanel1_2_1_1.add(jLabel1);
        jPanel1_2_1_1.add(jLabel2);
        jPanel1_2_1_1.add(jLabel3);
        jPanel1_2_1_1.add(jLabel4);
        jPanel1_2_1.add(new LaunchParaPanel(),BorderLayout.CENTER);


        /**
         * 类初始化时获取服务器1的发射和接收参数
         */
        File file = new File(System.getProperty("user.dir")+"\\"+"sever2SendSetting.txt");
        if (file.exists()){
            FileInputStream in = null;  //打开设置ip模态框显示上一次保存数据
            try {
                in = new FileInputStream(System.getProperty("user.dir")+"\\"+"sever2SendSetting.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] bytes = new byte[2048];
            int v = -1;
            String str = null;
            while ((v = in.read(bytes,0,bytes.length)) != -1){
                str = new String(bytes,0,v);
            }
            in.close();
            String[] sArray = str.split("&");
            mod_type1  = sArray[0];
            m1_gen1    = sArray[1];
            m1_init1   = sArray[2];
            m2_gen1    = sArray[3];
            m2_init1   = sArray[4];
            ecc_type1  = sArray[5];
            data_rate1 = sArray[6];
            rf_fac1    = sArray[7];
            slot_time1 = sArray[8];
        }


        File file1 = new File(System.getProperty("user.dir")+"\\"+"sever2ReceiveSetting.txt");
        if (file1.exists()){
            FileInputStream in1 = null;  //打开设置ip模态框显示上一次保存数据
            try {
                in1 = new FileInputStream(System.getProperty("user.dir")+"\\"+"sever2ReceiveSetting.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] bytes1 = new byte[2048];
            int v1 = -1;
            String str1 = null;
            while ((v1 = in1.read(bytes1,0,bytes1.length)) != -1){
                str1 = new String(bytes1,0,v1);
            }
            in1.close();
            String[] sArray1 = str1.split("&");
            mod_type2  = sArray1[0];
            m1_gen2    = sArray1[1];
            m1_init2   = sArray1[2];
            m2_gen2    = sArray1[3];
            m2_init2   = sArray1[4];
            ecc_type2  = sArray1[5];
            data_rate2 = sArray1[6];
            rf_fac2    = sArray1[7];
            slot_time2 = sArray1[8];
        }

        DataPanel.mod_type_send = null;
        DataPanel.m1_gen_send = null;
        DataPanel.m1_init_send = null;
        DataPanel.m2_gen_send = null;
        DataPanel.m2_init_send = null;
        DataPanel.ecc_type_send = null;
        DataPanel.data_rate_send = null;
        DataPanel.rf_fac_send = null;
        DataPanel.slot_time_send = null;
        DataPanel.mod_type_re = null;
        DataPanel.m1_gen_re = null;
        DataPanel.m1_init_re = null;
        DataPanel.m2_gen_re = null;
        DataPanel.m2_init_re = null;
        DataPanel.ecc_type_re = null;
        DataPanel.data_rate_re = null;
        DataPanel.rf_fac_re = null;
        DataPanel.slot_time_re = null;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1){   //点击发射单选按钮  显示发射参数配置面板
            jLabel1.setOpaque(true);
            jLabel1.setBackground(new Color(99,184,255));
            jLabel1.setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, new Color(99,184,255)));
            jLabel2.setBackground(null);
            jLabel2.setBorder(null);
            jPanel1_2_1.removeAll();
            jPanel1_2_1.add(jPanel1_2_1_1,BorderLayout.NORTH);
            jPanel1_2_1.add(new LaunchParaPanel(),BorderLayout.CENTER);
            LaunchParaPanel.jComboBox1.setSelectedItem(mod_type1);
            LaunchParaPanel.jtf1.setText(m1_gen1);
            LaunchParaPanel.jtf2.setText(m1_init1);
            LaunchParaPanel.jtf3.setText(m2_gen1);
            LaunchParaPanel.jtf4.setText(m2_init1);
            LaunchParaPanel.jComboBox2.setSelectedItem(ecc_type1);
            LaunchParaPanel.jtf5.setText(data_rate1);
            LaunchParaPanel.jComboBox3.setSelectedItem(rf_fac1);
            LaunchParaPanel.jtf6.setText(slot_time1);
            jPanel1_2_1.validate();
            repaint();
        } else if (e.getSource() == jb2){  //点击接收单选按钮  显示接收参数配置面板
            jLabel2.setOpaque(true);
            jLabel2.setBackground(new Color(99,184,255));
            jLabel2.setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, new Color(99,184,255)));
            jLabel1.setBorder(null);
            jLabel1.setBackground(null);
            jPanel1_2_1.removeAll();
            jPanel1_2_1.add(jPanel1_2_1_1,BorderLayout.NORTH);
            jPanel1_2_1.add(new ReceiveParaPanel(),BorderLayout.CENTER);
            ReceiveParaPanel.jComboBox1.setSelectedItem(mod_type2);
            ReceiveParaPanel.jtf1.setText(m1_gen2);
            ReceiveParaPanel.jtf2.setText(m1_init2);
            ReceiveParaPanel.jtf3.setText(m2_gen2);
            ReceiveParaPanel.jtf4.setText(m2_init2);
            ReceiveParaPanel.jComboBox2.setSelectedItem(ecc_type2);
            ReceiveParaPanel.jtf5.setText(data_rate2);
            ReceiveParaPanel.jComboBox3.setSelectedItem(rf_fac2);
            ReceiveParaPanel.jtf6.setText(slot_time2);
            jPanel1_2_1.validate();
            repaint();
        }
        else if(e.getSource() == jb3){  //点击收发单选按钮  显示发射参数配置面板（默认）
            jLabel1.setOpaque(true);
            jLabel1.setBackground(new Color(99,184,255));
            jLabel1.setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, new Color(99,184,255)));
            jLabel2.setBackground(null);
            jLabel2.setBorder(null);
            jPanel1_2_1.removeAll();
            jPanel1_2_1.add(jPanel1_2_1_1,BorderLayout.NORTH);
            jPanel1_2_1.add(new LaunchParaPanel(),BorderLayout.CENTER);
            LaunchParaPanel.jComboBox1.setSelectedItem(mod_type1);
            LaunchParaPanel.jtf1.setText(m1_gen1);
            LaunchParaPanel.jtf2.setText(m1_init1);
            LaunchParaPanel.jtf3.setText(m2_gen1);
            LaunchParaPanel.jtf4.setText(m2_init1);
            LaunchParaPanel.jComboBox2.setSelectedItem(ecc_type1);
            LaunchParaPanel.jtf5.setText(data_rate1);
            LaunchParaPanel.jComboBox3.setSelectedItem(rf_fac1);
            LaunchParaPanel.jtf6.setText(slot_time1);
            jPanel1_2_1.validate();
            repaint();
        }
        else if (e.getSource() == jButton) {   //点击配置按钮(1:发送配置帧2:打开单元格中相应的按钮)
            /**
             * 向服务器1发送配置信息
             */
            if(jb1.isSelected()) {       //发射单选按钮被选中（配置发射参数）
                if (LaunchParaPanel.jtf1.getText().length() == 0 || LaunchParaPanel.jtf2.getText().length() == 0   //文本框有没有填写的情况
                    || LaunchParaPanel.jtf3.getText().length() == 0 || LaunchParaPanel.jtf4.getText().length() == 0
                    || LaunchParaPanel.jtf5.getText().length() == 0 || LaunchParaPanel.jtf6.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(null, "请将所有的参数都填写完整！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                }else {  //发射参数面板且参数填写完整  （1：udp发送参数 2：持久化到本地 3：启用相关按钮）
                    //udp发送参数
                    String mod_type = (String) LaunchParaPanel.jComboBox1.getSelectedItem(); //调制方式
                    String m1_gen = LaunchParaPanel.jtf1.getText().trim();  //M序列1的生成多项式
                    String m1_init = LaunchParaPanel.jtf2.getText().trim();  //M序列1的初始状态
                    String m2_gen = LaunchParaPanel.jtf3.getText().trim();  //M序列2的生成多项式
                    String m2_init = LaunchParaPanel.jtf4.getText().trim();  //M序列1的初始状态
                    String ecc_type = (String) LaunchParaPanel.jComboBox2.getSelectedItem(); //纠错码类型
                    String data_rate = LaunchParaPanel.jtf5.getText().trim();  //数据速率
                    String rf_fac   = (String) LaunchParaPanel.jComboBox3.getSelectedItem(); //滚降系数
                    String slot_time = LaunchParaPanel.jtf6.getText().trim();  //时隙

                    String host = DataPanel.sever2Ip.trim();
                    Integer port = Integer.parseInt(DataPanel.sever2Port.trim());
                    try {
                        udpSend.sendmsg(configFrame1ser2.configPack(mod_type,m1_gen,m1_init,m2_gen,m2_init,ecc_type,data_rate,rf_fac,slot_time),host,port);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    // 同步发射速率到表格
                    DataPanel.table.setValueAt(data_rate + " Mbps",1,5);
                    //启用相关按钮
                    DataPanel.table.getColumn("发射模式").setCellRenderer(new ButtonRenderer2());
                    DataPanel.table.getColumn("发射模式").setCellEditor(new ButtonEditor2());
                    //持久化到本地
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(mod_type+"&"+m1_gen+"&"+m1_init);
                    stringBuffer.append("&"+m2_gen+"&"+m2_init);
                    stringBuffer.append("&"+ecc_type+"&"+data_rate);
                    stringBuffer.append("&"+rf_fac+"&"+slot_time);
                    byte[] bytes = stringBuffer.toString().getBytes();
                    String dir = System.getProperty("user.dir");
                    String path = dir+"\\"+"sever2SendSetting.txt";
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
                    //产生一次操作日志
                    CreateAndSaveLog.createSave("操作","配置二号服务器为发射模式");
                    this.setVisible(false);
                }
            }else if (jb2.isSelected()) {  //接收单选按钮被选中（配置接收参数）
                if (ReceiveParaPanel.jtf1.getText().length() == 0 || ReceiveParaPanel.jtf2.getText().length() == 0   //文本框有没有填写的情况
                        || ReceiveParaPanel.jtf3.getText().length() == 0 || ReceiveParaPanel.jtf4.getText().length() == 0
                        || ReceiveParaPanel.jtf5.getText().length() == 0 || ReceiveParaPanel.jtf6.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(null, "请将所有的参数都填写完整！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                }else {  //接收参数面板且参数填写完整  （1：udp发送参数 2：持久化到本地 3：启用相关按钮）
                    //udp发送参数
                    String mod_type = (String) ReceiveParaPanel.jComboBox1.getSelectedItem(); //调制方式
                    String m1_gen = ReceiveParaPanel.jtf1.getText().trim();  //M序列1的生成多项式
                    String m1_init = ReceiveParaPanel.jtf2.getText().trim();  //M序列1的初始状态
                    String m2_gen = ReceiveParaPanel.jtf3.getText().trim();  //M序列2的生成多项式
                    String m2_init = ReceiveParaPanel.jtf4.getText().trim();  //M序列1的初始状态
                    String ecc_type = (String) ReceiveParaPanel.jComboBox2.getSelectedItem(); //纠错码类型
                    String data_rate = ReceiveParaPanel.jtf5.getText().trim();  //数据速率
                    String rf_fac   = (String) ReceiveParaPanel.jComboBox3.getSelectedItem(); //滚降系数
                    String slot_time = ReceiveParaPanel.jtf6.getText().trim();  //时隙

                    String host = DataPanel.sever2Ip.trim();
                    Integer port = Integer.parseInt(DataPanel.sever2Port.trim());
                    try {
                        udpSend.sendmsg(configFrame2ser2.configPack(mod_type,m1_gen,m1_init,m2_gen,m2_init,ecc_type,data_rate,rf_fac,slot_time),host,port);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    // 同步发射速率到表格
                    DataPanel.table.setValueAt(data_rate + " Mbps",1,6);
                    //启用相关按钮
                    DataPanel.table.getColumn("接收模式").setCellRenderer(new ButtonRenderer2());
                    DataPanel.table.getColumn("接收模式").setCellEditor(new ButtonEditor2());
                    //持久化到本地
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(mod_type+"&"+m1_gen+"&"+m1_init);
                    stringBuffer.append("&"+m2_gen+"&"+m2_init);
                    stringBuffer.append("&"+ecc_type+"&"+data_rate);
                    stringBuffer.append("&"+rf_fac+"&"+slot_time);
                    byte[] bytes = stringBuffer.toString().getBytes();
                    String dir = System.getProperty("user.dir");
                    String path = dir+"\\"+"sever2ReceiveSetting.txt";
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
                    //产生一次操作日志
                    CreateAndSaveLog.createSave("操作","配置二号服务器为接收模式");
                    this.setVisible(false);
                }
            }else if (jb3.isSelected()) {  //收发单选按钮被选中
                //*********在选择收发模式单选按钮下，再点击配置按钮，此时同时把发射参数和接收参数发送至服务器***后续完成
                if (DataPanel.mod_type_send == null || DataPanel.m1_gen_send == null || DataPanel.m1_init_send == null || DataPanel.m2_gen_send == null || DataPanel.m2_init_send == null ||
                        DataPanel.ecc_type_send == null || DataPanel.data_rate_send == null || DataPanel.rf_fac_send == null || DataPanel.slot_time_send == null||
                        DataPanel.mod_type_re == null || DataPanel.m1_gen_re == null || DataPanel.m1_init_re == null || DataPanel.m2_gen_re == null || DataPanel.m2_init_re == null ||
                        DataPanel.ecc_type_re == null || DataPanel.data_rate_re == null || DataPanel.rf_fac_re == null || DataPanel.slot_time_re == null)
                {
                    JOptionPane.showMessageDialog(null, "请先检查并确认发射和接收参数！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    String host = DataPanel.sever2Ip.trim();
                    Integer port = Integer.parseInt(DataPanel.sever2Port.trim());
                    try {
                        udpSend.sendmsg(configFrame1ser2.configPack(DataPanel.mod_type_send,DataPanel.m1_gen_send,DataPanel.m1_init_send,DataPanel.m2_gen_send,DataPanel.m2_init_send,DataPanel.ecc_type_send,DataPanel.data_rate_send,DataPanel.rf_fac_send,DataPanel.slot_time_send),host,port);
                        udpSend.sendmsg(configFrame2ser2.configPack(DataPanel.mod_type_re,DataPanel.m1_gen_re,DataPanel.m1_init_re,DataPanel.m2_gen_re,DataPanel.m2_init_re,DataPanel.ecc_type_re,DataPanel.data_rate_re,DataPanel.rf_fac_re,DataPanel.slot_time_re),host,port);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    // 同步发射速率到表格
                    DataPanel.table.setValueAt(DataPanel.data_rate_send + " Mbps",1,5);
                    DataPanel.table.setValueAt(DataPanel.data_rate_re + " Mbps",1,6);
                    //启用相关按钮
                    DataPanel.table.getColumn("收发模式").setCellRenderer(new ButtonRenderer2());
                    DataPanel.table.getColumn("收发模式").setCellEditor(new ButtonEditor2());
                    //产生一次操作日志
                    CreateAndSaveLog.createSave("操作","配置二号服务器为收发模式");
                    this.setVisible(false);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jLabel1) {  //发射参数label点击事件
            if (jb3.isSelected()) {  //只有在收发单选按钮被选中后才起作用
                if (ReceiveParaPanel.jComboBox1 != null) {
                    if (ReceiveParaPanel.jtf1.getText().length() == 0 || ReceiveParaPanel.jtf2.getText().length() == 0   //文本框有没有填写的情况
                            || ReceiveParaPanel.jtf3.getText().length() == 0 || ReceiveParaPanel.jtf4.getText().length() == 0
                            || ReceiveParaPanel.jtf5.getText().length() == 0 || ReceiveParaPanel.jtf6.getText().length() == 0
                            || ReceiveParaPanel.jComboBox1.getSelectedItem() == null || ReceiveParaPanel.jComboBox2.getSelectedItem() == null || ReceiveParaPanel.jComboBox3.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "请将发射和接收参数都填写完整！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        DataPanel.mod_type_re = (String) ReceiveParaPanel.jComboBox1.getSelectedItem(); //调制方式
                        DataPanel.m1_gen_re = ReceiveParaPanel.jtf1.getText().trim();  //M序列1的生成多项式
                        DataPanel.m1_init_re = ReceiveParaPanel.jtf2.getText().trim();  //M序列1的初始状态
                        DataPanel.m2_gen_re = ReceiveParaPanel.jtf3.getText().trim();  //M序列2的生成多项式
                        DataPanel.m2_init_re = ReceiveParaPanel.jtf4.getText().trim();  //M序列1的初始状态
                        DataPanel.ecc_type_re = (String) ReceiveParaPanel.jComboBox2.getSelectedItem(); //纠错码类型
                        DataPanel.data_rate_re = ReceiveParaPanel.jtf5.getText().trim();  //数据速率
                        DataPanel.rf_fac_re = (String) ReceiveParaPanel.jComboBox3.getSelectedItem(); //滚降系数
                        DataPanel.slot_time_re = ReceiveParaPanel.jtf6.getText().trim();  //时隙

                        //持久化到本地
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(DataPanel.mod_type_re + "&" + DataPanel.m1_gen_re + "&" + DataPanel.m1_init_re);
                        stringBuffer.append("&" + DataPanel.m2_gen_re + "&" + DataPanel.m2_init_re);
                        stringBuffer.append("&" + DataPanel.ecc_type_re + "&" + DataPanel.data_rate_re);
                        stringBuffer.append("&" + DataPanel.rf_fac_re + "&" + DataPanel.slot_time_re);
                        byte[] bytes = stringBuffer.toString().getBytes();
                        String dir = System.getProperty("user.dir");
                        String path = dir + "\\" + "sever2ReceiveSetting.txt";
                        File file = new File(path);
                        File fileParent = file.getParentFile();
                        if (!fileParent.exists()) {
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
                    }
                }
                jLabel1.setOpaque(true);
                jLabel1.setBackground(new Color(99,184,255));
                jLabel1.setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, new Color(99,184,255)));
                jLabel2.setBackground(null);
                jLabel2.setBorder(null);
                jPanel1_2_1.removeAll();
                jPanel1_2_1.add(jPanel1_2_1_1, BorderLayout.NORTH);
                jPanel1_2_1.add(new LaunchParaPanel(), BorderLayout.CENTER);
                if (DataPanel.mod_type_send == null || DataPanel.m1_gen_send == null || DataPanel.m1_init_send == null || DataPanel.m2_gen_send == null || DataPanel.m2_init_send == null ||
                        DataPanel.ecc_type_send == null || DataPanel.data_rate_send == null || DataPanel.rf_fac_send == null || DataPanel.slot_time_send == null) {
                    LaunchParaPanel.jComboBox1.setSelectedItem(mod_type1);
                    LaunchParaPanel.jtf1.setText(m1_gen1);
                    LaunchParaPanel.jtf2.setText(m1_init1);
                    LaunchParaPanel.jtf3.setText(m2_gen1);
                    LaunchParaPanel.jtf4.setText(m2_init1);
                    LaunchParaPanel.jComboBox2.setSelectedItem(ecc_type1);
                    LaunchParaPanel.jtf5.setText(data_rate1);
                    LaunchParaPanel.jComboBox3.setSelectedItem(rf_fac1);
                    LaunchParaPanel.jtf6.setText(slot_time1);
                } else {
                    LaunchParaPanel.jComboBox1.setSelectedItem(DataPanel.mod_type_send);
                    LaunchParaPanel.jtf1.setText(DataPanel.m1_gen_send);
                    LaunchParaPanel.jtf2.setText(DataPanel.m1_init_send);
                    LaunchParaPanel.jtf3.setText(DataPanel.m2_gen_send);
                    LaunchParaPanel.jtf4.setText(DataPanel.m2_init_send);
                    LaunchParaPanel.jComboBox2.setSelectedItem(DataPanel.ecc_type_send);
                    LaunchParaPanel.jtf5.setText(DataPanel.data_rate_send);
                    LaunchParaPanel.jComboBox3.setSelectedItem(DataPanel.rf_fac_send);
                    LaunchParaPanel.jtf6.setText(DataPanel.slot_time_send);
                }

                jPanel1_2_1.validate();
                repaint();
            }
        }else if (e.getSource() == jLabel2) {  //接收参数label点击事件
            if (jb3.isSelected()) {  //只有在收发单选按钮被选中后才起作用
                if (LaunchParaPanel.jComboBox1 != null){
                    if (LaunchParaPanel.jtf1.getText().length() == 0 || LaunchParaPanel.jtf2.getText().length() == 0   //文本框有没有填写的情况
                            || LaunchParaPanel.jtf3.getText().length() == 0 || LaunchParaPanel.jtf4.getText().length() == 0
                            || LaunchParaPanel.jtf5.getText().length() == 0 || LaunchParaPanel.jtf6.getText().length() == 0
                            || LaunchParaPanel.jComboBox1.getSelectedItem() == null || LaunchParaPanel.jComboBox2.getSelectedItem() == null || LaunchParaPanel.jComboBox3.getSelectedItem() == null)
                    {
                        JOptionPane.showMessageDialog(null, "请将发射和接收参数都填写完整！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        DataPanel.mod_type_send = (String) LaunchParaPanel.jComboBox1.getSelectedItem(); //调制方式
                        DataPanel.m1_gen_send = LaunchParaPanel.jtf1.getText().trim();  //M序列1的生成多项式
                        DataPanel.m1_init_send = LaunchParaPanel.jtf2.getText().trim();  //M序列1的初始状态
                        DataPanel.m2_gen_send = LaunchParaPanel.jtf3.getText().trim();  //M序列2的生成多项式
                        DataPanel.m2_init_send = LaunchParaPanel.jtf4.getText().trim();  //M序列1的初始状态
                        DataPanel.ecc_type_send = (String) LaunchParaPanel.jComboBox2.getSelectedItem(); //纠错码类型
                        DataPanel.data_rate_send = LaunchParaPanel.jtf5.getText().trim();  //数据速率
                        DataPanel.rf_fac_send = (String) LaunchParaPanel.jComboBox3.getSelectedItem(); //滚降系数
                        DataPanel.slot_time_send = LaunchParaPanel.jtf6.getText().trim();  //时隙

                        //持久化到本地
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append( DataPanel.mod_type_send+"&"+ DataPanel.m1_gen_send+"&"+DataPanel.m1_init_send);
                        stringBuffer.append("&"+DataPanel.m2_gen_send+"&"+DataPanel.m2_init_send);
                        stringBuffer.append("&"+DataPanel.ecc_type_send+"&"+DataPanel.data_rate_send);
                        stringBuffer.append("&"+DataPanel.rf_fac_send+"&"+DataPanel.slot_time_send);
                        byte[] bytes = stringBuffer.toString().getBytes();
                        String dir = System.getProperty("user.dir");
                        String path = dir+"\\"+"sever2SendSetting.txt";
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
                    }
                }
                jLabel2.setOpaque(true);
                jLabel2.setBackground(new Color(99,184,255));
                jLabel2.setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, new Color(99,184,255)));
                jLabel1.setBorder(null);
                jLabel1.setBackground(null);
                jPanel1_2_1.removeAll();
                jPanel1_2_1.add(jPanel1_2_1_1,BorderLayout.NORTH);
                jPanel1_2_1.add(new ReceiveParaPanel(),BorderLayout.CENTER);
                if (DataPanel.mod_type_re == null || DataPanel.m1_gen_re == null || DataPanel.m1_init_re == null || DataPanel.m2_gen_re == null || DataPanel.m2_init_re == null ||
                        DataPanel.ecc_type_re == null || DataPanel.data_rate_re == null || DataPanel.rf_fac_re == null || DataPanel.slot_time_re == null){

                    ReceiveParaPanel.jComboBox1.setSelectedItem(mod_type2);
                    ReceiveParaPanel.jtf1.setText(m1_gen2);
                    ReceiveParaPanel.jtf2.setText(m1_init2);
                    ReceiveParaPanel.jtf3.setText(m2_gen2);
                    ReceiveParaPanel.jtf4.setText(m2_init2);
                    ReceiveParaPanel.jComboBox2.setSelectedItem(ecc_type2);
                    ReceiveParaPanel.jtf5.setText(data_rate2);
                    ReceiveParaPanel.jComboBox3.setSelectedItem(rf_fac2);
                    ReceiveParaPanel.jtf6.setText(slot_time2);
                }else {
                    ReceiveParaPanel.jComboBox1.setSelectedItem(DataPanel.mod_type_re);
                    ReceiveParaPanel.jtf1.setText(DataPanel.m1_gen_re);
                    ReceiveParaPanel.jtf2.setText(DataPanel.m1_init_re);
                    ReceiveParaPanel.jtf3.setText(DataPanel.m2_gen_re);
                    ReceiveParaPanel.jtf4.setText(DataPanel.m2_init_re);
                    ReceiveParaPanel.jComboBox2.setSelectedItem(DataPanel.ecc_type_re);
                    ReceiveParaPanel.jtf5.setText(DataPanel.data_rate_re);
                    ReceiveParaPanel.jComboBox3.setSelectedItem(DataPanel.rf_fac_re);
                    ReceiveParaPanel.jtf6.setText(DataPanel.slot_time_re);
                }
                jPanel1_2_1.validate();
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == jb1){
            jb1.setBackground(new Color(99,184,255));
            jb2.setBackground(new Color(135,206,255));
            jb3.setBackground(new Color(135,206,255));
            jLabel1.setCursor(null);
            jLabel2.setCursor(null);
        }else if (e.getSource() == jb2){
            jb2.setBackground(new Color(99,184,255));
            jb1.setBackground(new Color(135,206,255));
            jb3.setBackground(new Color(135,206,255));
            jLabel1.setCursor(null);
            jLabel2.setCursor(null);
        }else if (e.getSource() == jb3){
            jb3.setBackground(new Color(99,184,255));
            jb1.setBackground(new Color(135,206,255));
            jb2.setBackground(new Color(135,206,255));
            jLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == jButton){
            jButton.setForeground(new Color(0,191,255));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == jButton){
            jButton.setForeground(Color.white);
        }
    }
}
