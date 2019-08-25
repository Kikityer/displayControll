package com.hdh.rendererAndEditor;

import com.hdh.WindowTest;
import com.hdh.codeFrame.server1.*;
import com.hdh.modelPanel.ReceiveDataPanel;
import com.hdh.page.DataPanel;
import com.hdh.page.StarPicPanel;
import com.hdh.page.WindowInitial;
import com.hdh.util.CreateAndSaveLog;
import com.hdh.util.UdpSend;
import com.hdh.util.UdpSendFreeThread1;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;

/**
 * @ClassName ButtonEditor1
 * @Description TODO  服务器1在编辑结束之后显示重新绘制编辑器 （开始控制接收发送文件）
 * @Author Kikityer
 * @Date 2019/3/21 14:49
 * @Version 1.0.0
 **/
public class ButtonEditor1 extends DefaultCellEditor{
    /**
     * 发单元格按钮的事件的绑定
     */
    private JPanel panel;
    private JButton button;
    private int row;
    private int column;
    String value;

    UdpSend udpSend = new UdpSend();
    public static UdpSendFreeThread1 udpSendFreeThread1 = new UdpSendFreeThread1();
    ControlFrame1ser1 controlFrame1ser1 = new ControlFrame1ser1();
    ControlFrame2ser1 controlFrame2ser1 = new ControlFrame2ser1();
    ControlFrame3ser1 controlFrame3ser1 = new ControlFrame3ser1();
    ControlFrame4ser1 controlFrame4ser1 = new ControlFrame4ser1();
    ControlFrame5ser1 controlFrame5ser1 = new ControlFrame5ser1();
    ControlFrame6ser1 controlFrame6ser1 = new ControlFrame6ser1();
    public static Thread thread = null;

    public ButtonEditor1() {
        super(new JTextField());
        this.setClickCountToStart(1);
        this.initButton();
        this.initPanel();
        // 添加按钮。
        this.panel.add(this.button);
    }

    @Deprecated
    private void initButton(){
        this.button = new JButton();
        // 设置按钮的大小及位置。
        float a = (WindowInitial.dataPanelWidth)/14;
        float b = (float) ((WindowInitial.dataPanelHeight*0.8-50)/4);
        this.button.setBounds((int) (a*0.1), (int) (b*0.35), (int) (a*0.8), (int) (b*0.3));
        // 为按钮添加事件。这里只能添加ActionListner事件，Mouse事件无效。
        this.button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (row == 0 && column == 1){
                    if (value.equals("启动")) {   //向服务器1地址发送开始发送控制帧 *********后续增加
                        button.setText("关闭");
                        //向服务器1发送“开始发送”控制帧
                        try {
                            udpSend.sendmsg(controlFrame1ser1.controllPack1(), DataPanel.sever1Ip,Integer.parseInt(DataPanel.sever1Port));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        //向服务器1发送空闲帧
                        udpSendFreeThread1.sercerInit();
                        thread = new Thread(udpSendFreeThread1);
                        thread.start();
                        //将发送文件按钮启用
                        DataPanel.table.getColumn("发送文件").setCellRenderer(new ButtonRenderer1());
                        DataPanel.table.getColumn("发送文件").setCellEditor(new ButtonEditor1());
                        //产生一个日志并持久化到本地
                        CreateAndSaveLog.createSave("操作","向一号服务器发送开始发射控制");
                        fireEditingStopped();
                    }else if (value.equals("关闭")) {  //向服务器1地址发送结束发送控制帧
                        button.setText("启动");
                        //停止空闲帧线程
                        thread.stop();
                        udpSendFreeThread1.closeServer();
                        //向服务器1发送“结束发送”控制帧
                        try {
                            udpSend.sendmsg(controlFrame2ser1.controllPack2(),DataPanel.sever1Ip,Integer.parseInt(DataPanel.sever1Port));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        //禁用发送文件按钮
                        DataPanel.table.getColumn("发射模式").setCellRenderer(new ButtonRenderer());
                        DataPanel.table.getColumn("发射模式").setCellEditor(null);
                        DataPanel.table.getColumn("发送文件").setCellRenderer(new ButtonRenderer());
                        DataPanel.table.getColumn("发送文件").setCellEditor(null);
                        //产生一次操作日志并持久化到本地
                        CreateAndSaveLog.createSave("操作","向一号服务器发送结束发射控制");
                        fireEditingStopped();
                    }
                }else if (row == 0 && column == 2){
                    if (value.equals("启动")) {   //向服务器1地址发送开始接收控制帧
                        button.setText("关闭");
                        //向服务器1发送“开始接收”控制帧
                        try {
                            udpSend.sendmsg(controlFrame3ser1.controllPack3(),DataPanel.sever1Ip,Integer.parseInt(DataPanel.sever1Port));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        //产生一次操作日志并持久化到本地
                        CreateAndSaveLog.createSave("操作","向一号服务器发送开始接收控制");
                        //弹出接收数据模态框
                        ReceiveDataPanel receiveDataPanel = new ReceiveDataPanel(WindowTest.windowInitial);
                        receiveDataPanel.setModal(true);
                        receiveDataPanel.setTitle("一号服务器接收数据显示");
                        receiveDataPanel.setSize(710, 600);
                        receiveDataPanel.setResizable(false);
                        receiveDataPanel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        receiveDataPanel.setLocationRelativeTo(WindowTest.windowInitial);
                        receiveDataPanel.setVisible(true);
                        fireEditingStopped();
                    }else if (value.equals("关闭")) {  //向服务器1地址发送结束接收控制帧
                        button.setText("启动");
                        //向服务器1发送“结束接收”控制帧
                        try {
                            udpSend.sendmsg(controlFrame4ser1.controllPack4(),DataPanel.sever1Ip,Integer.parseInt(DataPanel.sever1Port));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        //禁用接收模式下发射按钮
                        DataPanel.table.getColumn("接收模式").setCellRenderer(new ButtonRenderer());
                        DataPanel.table.getColumn("接收模式").setCellEditor(null);
                        //产生一次操作日志并持久化到本地
                        CreateAndSaveLog.createSave("操作","向一号服务器发送结束接收控制");
                        fireEditingStopped();
                    }
                }else if (row == 0 && column == 3){
                    if (value.equals("启动")) {   //向服务器1地址发送开始接收控制帧
                        button.setText("关闭");
                        //向服务器1发送“开始发送和开始接收”控制帧
                        try {
                            udpSend.sendmsg(controlFrame5ser1.controllPack5(),DataPanel.sever1Ip,Integer.parseInt(DataPanel.sever1Port));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        //向服务器1发送空闲帧
                        udpSendFreeThread1.sercerInit();
                        thread = new Thread(udpSendFreeThread1);
                        thread.start();
                        //将发送文件按钮启用
                        DataPanel.table.getColumn("发送文件").setCellRenderer(new ButtonRenderer1());
                        DataPanel.table.getColumn("发送文件").setCellEditor(new ButtonEditor1());
                        //产生一次操作日志并持久化到本地
                        CreateAndSaveLog.createSave("操作","向一号服务器发送开始发射和接收控制");
                        //弹出接收数据模态框
                        ReceiveDataPanel receiveDataPanel = new ReceiveDataPanel(WindowTest.windowInitial);
                        receiveDataPanel.setModal(true);
                        receiveDataPanel.setTitle("一号服务器接收数据显示");
                        receiveDataPanel.setSize(710, 600);
                        receiveDataPanel.setResizable(false);
                        receiveDataPanel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        receiveDataPanel.setLocationRelativeTo(WindowTest.windowInitial);
                        receiveDataPanel.setVisible(true);
                        fireEditingStopped();
                    }else if (value.equals("关闭")) {  //向服务器1地址发送结束接收控制帧
                        button.setText("启动");
                        //停止空闲帧线程
                        thread.stop();
                        udpSendFreeThread1.closeServer();
                        //向服务器1发送“结束发送和结束接收”控制帧
                        try {
                            udpSend.sendmsg(controlFrame6ser1.controllPack6(),DataPanel.sever1Ip,Integer.parseInt(DataPanel.sever1Port));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        //禁用发送文件按钮
                        DataPanel.table.getColumn("收发模式").setCellRenderer(new ButtonRenderer());
                        DataPanel.table.getColumn("收发模式").setCellEditor(null);
                        DataPanel.table.getColumn("发送文件").setCellRenderer(new ButtonRenderer());
                        DataPanel.table.getColumn("发送文件").setCellEditor(null);
                        //产生一个日志并持久化到本地
                        CreateAndSaveLog.createSave("操作","向一号服务器发送结束发射和接收控制");
                        fireEditingStopped();
                    }
                }else if (row == 0 && column == 4){ //打开文件选择器
                    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    jfc.setDialogTitle("选取文件发送");
                    jfc.setApproveButtonText("发送");
                    jfc.setAcceptAllFileFilterUsed(false);
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("文件", "png", "gif","jpg","txt","bmp","tif");
                    jfc.addChoosableFileFilter(filter);

                    DataFrameser1 dataFrameser1 = new DataFrameser1();
                    int returnValue = jfc.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        FileImageInputStream input = null;
                        try {
                            String path = jfc.getSelectedFile().getPath();
                            input = new FileImageInputStream(new File(path));
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            byte[] buf = new byte[1024];
                            int numBytesRead = 0;
                            while ((numBytesRead = input.read(buf)) != -1){
                                baos.write(buf,0,numBytesRead);
                            }

                            baos.close();
                            input.close();
                            /**
                             * 将读取的图片文件进一步操作
                             */
                            //停止空闲线程
                            thread.stop();
                            udpSendFreeThread1.closeServer();
                            //将选取的文件转成字节数组发送出去
                            udpSend.sendmsg(dataFrameser1.dataPack(baos.toByteArray()),DataPanel.sever1Ip,Integer.parseInt(DataPanel.sever1Port));
                            Thread.sleep(1000);
                            //发送文件结束之后停止一秒 又开始发送空闲帧
                            udpSendFreeThread1.sercerInit();
                            thread = new Thread(udpSendFreeThread1);
                            thread.start();
                            //*****在发送完一个文件之后，以日志的形式显示在日志区
                            CreateAndSaveLog.createSave("发送文件","成功发送["+jfc.getSelectedFile().getParent()+"]路径下，文件名为《"+jfc.getSelectedFile().getName()+"》的文件");
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }
    private void initPanel()
    {
        this.panel = new JPanel();
        // panel使用绝对定位，这样button就不会充满整个单元格。
        this.panel.setLayout(null);
    }
    /**
     * 这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格）
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {

        this.row = row;
        this.column = column;
        this.value = (String) value;
        this.button.setForeground(Color.black);
        this.button.setFont(new Font("Microsoft Yahei",Font.PLAIN,15));

        if (column == 4){
            this.button.setText("<html>选择<br>文件</html>");
        }else {
            this.button.setText((value == null) ? "" : value.toString());
        }
        return this.panel;
    }

    /**
     * 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。
     */
    @Override
    public Object getCellEditorValue()
    {
        return this.button.getText();
    }
}
