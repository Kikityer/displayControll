package com.hdh.page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

/**
 * @ClassName WindowInitial
 * @Description TODO 整个应用只有一个页面，包括三个版面（1：数传区（菜单栏、标题、数传表格） 2：日志区  3：星座图区）
 * @Author Kikityer
 * @Date 2019/3/21 15:07
 * @Version 1.0.0
 **/
public class WindowInitial extends JFrame{

    Point pressedPoint;
    JPanel jPanel1;  //装载星座图
    JPanel jPanel2;  //装载数传区和日志区
    public static int screenWidth;
    public static int screenHeight;
    public static int dataPanelWidth;
    public static int dataPanelHeight;
    public WindowInitial() throws IOException {
        this.setUndecorated(true);// 取消窗体修饰效果


        /**
         * 窗体鼠标移动事件
         */
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { //鼠标按下事件
                pressedPoint = e.getPoint(); //记录鼠标坐标
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) { // 鼠标拖拽事件
                Point point = e.getPoint();// 获取当前坐标
                Point locationPoint = getLocation();// 获取窗体坐标
                int x = locationPoint.x + point.x - pressedPoint.x;// 计算移动后的新坐标
                int y = locationPoint.y + point.y - pressedPoint.y;
                setLocation(x, y);// 改变窗体位置
            }
        });

        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        screenWidth = screenSize.width; // 获取屏幕的宽
        screenHeight = screenSize.height; // 获取屏幕的高
        dataPanelWidth = (int) (screenSize.width*0.78);
        dataPanelHeight = (int) (screenSize.height*0.7);
        this.setSize(new Dimension(screenWidth,screenHeight));

        this.setTitle("");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel1 = new JPanel();  //装载左边星图
        jPanel1.setLayout(new BorderLayout());
        jPanel1.setPreferredSize(new Dimension(WindowInitial.screenWidth-WindowInitial.dataPanelWidth,WindowInitial.screenHeight));
        jPanel1.add(new StarPicPanel());
        this.add(jPanel1,BorderLayout.EAST);

        jPanel2 = new JPanel(); //装载右边数据区和日志区
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(new LogPanel(),BorderLayout.SOUTH);
        jPanel2.add(new DataPanel(),BorderLayout.CENTER);
        this.add(jPanel2,BorderLayout.CENTER);
    }

}
