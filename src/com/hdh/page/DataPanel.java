package com.hdh.page;

import com.hdh.WindowTest;
import com.hdh.modelPanel.MyDialogIp;
import com.hdh.rendererAndEditor.ButtonEditor;
import com.hdh.rendererAndEditor.ButtonRenderer;
import com.hdh.rendererAndEditor.IconRenderer;
import com.hdh.util.GradientPanel;
import org.jvnet.substance.SubstanceTableUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/**
 * @ClassName DataPanel
 * @Description TODO  数据显示区
 * @Author Kikityer
 * @Date 2019/3/21 14:45
 * @Version 1.0.0
 **/
public class DataPanel extends JPanel implements ActionListener,MouseListener{

    public static long lastTime1,lastTime2,lastTime3,lastTime4;
    public static long conditionFrameLastTime1,conditionFrameLastTime2,conditionFrameLastTime3,conditionFrameLastTime4;
    JPanel jPanel1; //装载菜单栏
    JPanel jPanel1_1,jPanel1_2,jPanel1_3;
    JPanel jPanel2; //装载标题
    JPanel jPanel2_1,jPanel2_2;
    JPanel jPanel3; //装载数据传输表格

    public static String sever1Ip;
    public static String sever1Port;
    public static String sever2Ip;
    public static String sever2Port;
    public static String sever3Ip;
    public static String sever3Port;
    public static String sever4Ip;
    public static String sever4Port;
    public static String severWaveIp;
    public static String severWavePort;

    JButton jButton1,jButton2;
    JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel8,jLabel9,jLabel10,jLabel11,jLabel12,jLabel13,jLabel14,jLabel15,jLabel16;

    JScrollPane jscrollpane = new JScrollPane();
    public static DefaultTableModel tableModel;
    public static JTable table;

    //收发模式下，用于存储发射和接收参数，在点击配置按钮之后，将存储的数据发送出去
    public static String mod_type_send;
    public static String m1_gen_send;
    public static String m1_init_send;
    public static String m2_gen_send;
    public static String m2_init_send;
    public static String ecc_type_send;
    public static String data_rate_send;
    public static String rf_fac_send;
    public static String slot_time_send;

    public static String mod_type_re;
    public static String m1_gen_re;
    public static String m1_init_re;
    public static String m2_gen_re;
    public static String m2_init_re;
    public static String ecc_type_re;
    public static String data_rate_re;
    public static String rf_fac_re;
    public static String slot_time_re;

    public static LinkedList<byte[]> linkedList = new LinkedList<>();   //用户缓冲区，将接收到的数据先全部存储在此,给另外一个线程处理
    public static HashMap<Integer,byte[]> hashMap = new HashMap<>();    //处理完的数据包按照顺序放入本数组中
    public static ArrayList<byte[]> arrayList = new ArrayList<>();


    DataPanel() throws IOException {
        this.setLayout(new BorderLayout());

        jPanel1 = new GradientPanel(new GridLayout(1,8,0,0));//装载菜单栏
        jPanel1.setOpaque(true);
        jPanel1.setPreferredSize(new Dimension(WindowInitial.dataPanelWidth, (int) (WindowInitial.dataPanelHeight*0.08)));
        jPanel1_1 = new JPanel();
        jPanel1_1.setLayout(null);
        jPanel1_1.setOpaque(false);
        jPanel1_2 = new JPanel();
        jPanel1_2.setLayout(null);
        jPanel1_2.setOpaque(false);
        jPanel1_3 = new JPanel();
        jPanel1_3.setLayout(new BorderLayout());
        jPanel1_3.setOpaque(false);
        Icon setting = new ImageIcon(getClass().getResource("/com/hdh/img/setting.png"));
        jButton1 = new JButton("IP设置");
        Font f = new Font("Microsoft Yahei",Font.BOLD,14);
        jButton1.setFont(f);
        jButton1.setIcon(setting);
        jButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        float a = (WindowInitial.dataPanelWidth)/8;
        float b = (float) ((WindowInitial.dataPanelHeight*0.08));
        jButton1.setBounds((int) (a*0.2),(int) (b*0.15),(int)(a*0.6),(int) (b*0.7));
        jButton1.addActionListener(this);
//        jButton1.addMouseListener(this);
        Icon exit = new ImageIcon(getClass().getResource("/com/hdh/img/exit.png"));
        jButton2 = new JButton("退出");
        jButton2.setFont(f);
        jButton2.setIcon(exit);
        jButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton2.setBounds(0,(int) (b*0.15),(int)(a*0.6),(int) (b*0.7));
        jButton2.addActionListener(this);
//        jButton2.addMouseListener(this);
        jLabel1 = new JLabel("",SwingConstants.CENTER);
        Icon menu = new ImageIcon(getClass().getResource("/com/hdh/img/logo2.png"));
        jLabel1.setIcon(menu);
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel("中国电科第三十八研究所",SwingConstants.CENTER);
        jLabel6.setFont(new Font("Microsoft Yahei",Font.BOLD, 13));
        jLabel6.setForeground(Color.white);
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
        jPanel1_1.add(jButton1);
        jPanel1_2.add(jButton2);
        jPanel1_3.add(jLabel1);
        jPanel1.add(jPanel1_3);
        jPanel1.add(jPanel1_1);
        jPanel1.add(jPanel1_2);
        jPanel1.add(jLabel2);
        jPanel1.add(jLabel3);
        jPanel1.add(jLabel4);
        jPanel1.add(jLabel5);
        jPanel1.add(jLabel6);
//        jPanel1.add(jLabel7);
//        jPanel1.add(jLabel8);
//        jPanel1.add(jLabel9);
//        jPanel1.add(jLabel10);
//        jPanel1.add(jLabel11);
//        jPanel1.add(jLabel12);
//        jPanel1.add(jLabel13);




        jPanel2 = new GradientPanel(new GridLayout(1,3)); //装载标题\
//        jPanel2.setBackground(new Color(28,134,238));
//        jPanel2.setLayout(new GridLayout(1,3));
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        jPanel2.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(30,144,255)));
        jPanel2_2 = new JPanel();
        jPanel2_2.setOpaque(false);
        jPanel2_2.setLayout(new BorderLayout());
        jLabel14 = new JLabel();
//        jLabel14.setSize( WindowInitial.dataPanelWidth/3, (int) (WindowInitial.dataPanelHeight*0.12));
//        Icon logo = new ImageIcon(getClass().getResource("/com/hdh/img/logo.png"));
//        jLabel14.setIcon(logo);
        jPanel2_2.add(jLabel14);
        jLabel15 = new JLabel("雷达通信服务器显控系统",JLabel.CENTER);
        jLabel15.setFont(new Font("Microsoft Yahei", Font.BOLD, 35));
        jLabel15.setForeground(Color.white);
        jLabel16 = new JLabel();
//        jLabel16.setFont(new Font("宋体", Font.PLAIN, 15));
        jPanel2_1 = new JPanel();
        jPanel2_1.setOpaque(false);
        jPanel2_1.setLayout(null);
        jLabel16.setBounds(0, (int) (WindowInitial.dataPanelHeight*0.12*0.5),320,17);
        jPanel2_1.add(jLabel16);
        jPanel2.add(jPanel2_2);
        jPanel2.add(jLabel15);
        jPanel2.add(jPanel2_1);

/**
 * 目标用户 发射模式 接收模式 收发模式  发送文件  发射速率 接收速率  捕获 载同步 码同步 符号同步 帧同步 链路指示 参数配置
 * 参数配置模态框
 * 接收数据模态框
 */
        jPanel3 = new JPanel(); //装载数据传输表格
        jPanel3.setLayout(new BorderLayout());
        jPanel3.setPreferredSize(new Dimension(WindowInitial.dataPanelWidth, (int) (WindowInitial.dataPanelHeight*0.8)));
        /**
         * 数据传输面板表格设置
         */
        jscrollpane.setAutoscrolls(true);
        tableModel = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return (column == 13 || column == 1 || column == 2 || column == 3 || column == 4 || column == 12);
            }

        };
        String headName[] = {"目标用户","发射模式","接收模式","收发模式","发送文件","发射速率","接收速率","捕获","载同步","码同步","符号同步","帧同步","链路指示","参数配置"};
        tableModel.setColumnIdentifiers(headName);
        table = new JTable(tableModel);
        table.getColumn("发射模式").setCellRenderer(new ButtonRenderer());
        table.getColumn("接收模式").setCellRenderer(new ButtonRenderer());
        table.getColumn("收发模式").setCellRenderer(new ButtonRenderer());
        table.getColumn("发送文件").setCellRenderer(new ButtonRenderer());
        table.getColumn("参数配置").setCellRenderer(new ButtonRenderer());
        table.getColumn("参数配置").setCellEditor(new ButtonEditor());
        table.getColumn("捕获").setCellRenderer(new IconRenderer());
        table.getColumn("载同步").setCellRenderer(new IconRenderer());
        table.getColumn("码同步").setCellRenderer(new IconRenderer());
        table.getColumn("符号同步").setCellRenderer(new IconRenderer());
        table.getColumn("帧同步").setCellRenderer(new IconRenderer());
        table.getColumn("链路指示").setCellRenderer(new IconRenderer());

        table.setRowSelectionAllowed(false);  //关闭选择一行
        table.setShowGrid(false);  //是否显示网格
        table.setShowHorizontalLines(true); //显示横线网格
        // 设置列标题不能移动
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jscrollpane.setViewportView(table);

        table.getTableHeader().setPreferredSize(new Dimension(1, 50));//设置表单的表头高度
        table.getTableHeader().setFont(new Font("Microsoft Yahei", Font.BOLD, 14));
        table.setBackground(new Color(176,226,255));
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Microsoft Yahei",Font.PLAIN,15));
        table.setRowHeight((int) ((WindowInitial.dataPanelHeight*0.8-50)/4));  //表格每行的高度
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        for(int i=1;i<5;i++){
            Vector vector = new Vector();
            vector.addElement("服务器"+i);
            vector.addElement("启动");
            vector.addElement("启动");
            vector.addElement("启动");
            vector.addElement("");
            vector.addElement("0 Mbps");
            vector.addElement("0 Mbps");
            vector.addElement("");
            vector.addElement("");
            vector.addElement("");
            vector.addElement("");
            vector.addElement("");
            vector.addElement("");
            vector.addElement("编辑");

            tableModel.getDataVector().add(vector);
        }

        jPanel3.add(jscrollpane,BorderLayout.CENTER);


         this.add(jPanel1,BorderLayout.NORTH);
         this.add(jPanel2,BorderLayout.CENTER);
         this.add(jPanel3,BorderLayout.SOUTH);

        /**
         * 在表格数据区初始的时候将所有的服务器的IP地址和端口获取
         */
        File file = new File(System.getProperty("user.dir")+"\\"+"ipCache.txt");
        if (file.exists()){
            FileInputStream in = null;  //打开设置ip模态框显示上一次保存数据
            try {
                in = new FileInputStream(System.getProperty("user.dir")+"\\"+"ipCache.txt");
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
            //将读取到IP和端口文件填写到模态框中
            String[] sArray = str.split("&");
            sever1Ip = sArray[0];
            sever1Port = sArray[1];
            sever2Ip = sArray[2];
            sever2Port = sArray[3];
            sever3Ip = sArray[4];
            sever3Port = sArray[5];
            sever4Ip = sArray[6];
            sever4Port = sArray[7];
            severWaveIp = sArray[8];
            severWavePort = sArray[9];
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton2){
            int n = JOptionPane.showConfirmDialog(null, "确认要退出系统吗?", "温馨提示", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }else if (e.getSource() == jButton1){   //触发ip网络设置按钮事件
            MyDialogIp dialog = new MyDialogIp(WindowTest.windowInitial);
            // 设置为模态
            dialog.setModal(true);
            dialog.setTitle("IP设置");
            dialog.setSize(400, 650);
            dialog.setResizable(false);
            dialog.setLocationRelativeTo(WindowTest.windowInitial);

            File file = new File(System.getProperty("user.dir")+"\\"+"ipCache.txt");
            if (file.exists()){
                try {
                    FileInputStream in = new FileInputStream(System.getProperty("user.dir")+"\\"+"ipCache.txt");  //打开设置ip模态框显示上一次保存数据
                    byte[] bytes = new byte[2048];
                    int v = -1;
                    String str = null;
                    while ((v = in.read(bytes,0,bytes.length)) != -1){
                        str = new String(bytes,0,v);
                    }
                    in.close();
                    //将读取到IP和端口文件填写到模态框中
                    String[] sArray = str.split("&");
                    MyDialogIp.jTextField1.setText(sArray[0]);
                    MyDialogIp.jTextField2.setText(sArray[1]);
                    MyDialogIp.jTextField3.setText(sArray[2]);
                    MyDialogIp.jTextField4.setText(sArray[3]);
                    MyDialogIp.jTextField5.setText(sArray[4]);
                    MyDialogIp.jTextField6.setText(sArray[5]);
                    MyDialogIp.jTextField7.setText(sArray[6]);
                    MyDialogIp.jTextField8.setText(sArray[7]);
                    MyDialogIp.jTextField9.setText(sArray[8]);
                    MyDialogIp.jTextField10.setText(sArray[9]);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            dialog.setVisible(true);
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

        if (e.getSource() == jButton1){
            jButton1.setForeground(new Color(0,191,255));
            jPanel1_1.setBackground(new Color(0,191,255));
        }else if (e.getSource() == jButton2){
            jButton2.setForeground(new Color(0,191,255));
            jPanel1_2.setBackground(new Color(0,191,255));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == jButton1){
            jButton1.setForeground(Color.white);
            jPanel1_1.setBackground(new Color(0,154,205));
        }else if (e.getSource() == jButton2){
            jButton2.setForeground(Color.white);
            jPanel1_2.setBackground(new Color(0,154,205));
        }
    }
}
