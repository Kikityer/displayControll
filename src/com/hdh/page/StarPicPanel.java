package com.hdh.page;

import com.hdh.jfreeChart.JFreeChartUser1;
import com.hdh.jfreeChart.JFreeChartUser2;
import com.hdh.jfreeChart.JFreeChartUser3;
import com.hdh.jfreeChart.JFreeChartUser4;
import com.hdh.util.CreateAndSaveLog;
import com.hdh.util.GradientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName StarPicPanel
 * @Description TODO  当向服务器发送空闲帧后，服务器向下传递到终端，实现终端入锁，服务器返回状态帧（其中包括IQ信息，即为星图的x轴和y轴的数据）
 * @Author Kikityer
 * @Date 2019/3/21 14:43
 * @Version 1.0.0
 **/
public class StarPicPanel extends JPanel implements ActionListener,MouseListener{

    /**
     * 当向服务器发送空闲帧之后，服务器返回状态帧，为全1 .可选择文件发送。同时接收IO数据，绘制星图。
     */
    JPanel jPanel1; //在星图显示区下面放置四个删除按钮
    JPanel jPanel1_1,jPanel1_2,jPanel1_3,jPanel1_4;
    JPanel jPanel2; //在星图显示区上面放置星图

    JButton jButton1,jButton2,jButton3,jButton4;
    public static int linkFlag1;
    public static int linkFlag2;
    public static int linkFlag3;
    public static int linkFlag4;
    public static int conditionFlag1,conditionFlag2,conditionFlag3,conditionFlag4;



    StarPicPanel(){
        this.setLayout(new BorderLayout());

        jPanel1 = new GradientPanel(new GridLayout(1,4));  //放置删除按钮
//        jPanel1.setLayout(new GridLayout(1,4));
        jPanel1.setBorder(BorderFactory.createMatteBorder(0,1,0,0,new Color(30,144,255)));
        jPanel1.setPreferredSize(new Dimension(WindowInitial.screenWidth-WindowInitial.dataPanelWidth, (int) (WindowInitial.screenHeight*0.05+1)));
        jPanel1_1 = new JPanel();
        jPanel1_1.setOpaque(false);
//        jPanel1_1.setBackground(Color.white);
        jPanel1_2 = new JPanel();
        jPanel1_2.setOpaque(false);
//        jPanel1_2.setBackground(Color.white);
        jPanel1_3 = new JPanel();
        jPanel1_3.setOpaque(false);
//        jPanel1_3.setBackground(Color.white);
        jPanel1_4 = new JPanel();
        jPanel1_4.setOpaque(false);
//        jPanel1_4.setBackground(Color.white);
        jButton1 = new JButton("清除1数据");
        Font f = new Font("Microsoft Yahei",Font.BOLD,14);
        jButton1.setFont(f);
        jButton1.setPreferredSize(new Dimension((int) ((WindowInitial.screenWidth*0.22)/4-10), (int) (WindowInitial.screenHeight*0.05*0.6)));
        jButton1.addActionListener(this);
//        jButton1.addMouseListener(this);
        jButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton2 = new JButton("清除2数据");
        jButton2.setFont(f);
        jButton2.setPreferredSize(new Dimension((int) ((WindowInitial.screenWidth*0.22)/4-10), (int) (WindowInitial.screenHeight*0.05*0.6)));
        jButton2.addActionListener(this);
//        jButton2.addMouseListener(this);
        jButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton3 = new JButton("清除3数据");
        jButton3.setFont(f);
        jButton3.setPreferredSize(new Dimension((int) ((WindowInitial.screenWidth*0.22)/4-10), (int) (WindowInitial.screenHeight*0.05*0.6)));
        jButton3.addActionListener(this);
//        jButton3.addMouseListener(this);
        jButton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton4 = new JButton("清除4数据");
        jButton4.setFont(f);
        jButton4.setPreferredSize(new Dimension((int) ((WindowInitial.screenWidth*0.22)/4-10), (int) (WindowInitial.screenHeight*0.05*0.6)));
        jButton4.addActionListener(this);
//        jButton4.addMouseListener(this);
        jButton4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jPanel1_1.add(jButton1);
        jPanel1_2.add(jButton2);
        jPanel1_3.add(jButton3);
        jPanel1_4.add(jButton4);

        jPanel1.add(jPanel1_1);
        jPanel1.add(jPanel1_2);
        jPanel1.add(jPanel1_3);
        jPanel1.add(jPanel1_4);

        jPanel2 = new JPanel();  //放置星座图
        jPanel2.setLayout(new GridLayout(4,1));
        jPanel2.add(JFreeChartUser1.drawChart());
        jPanel2.add(JFreeChartUser2.drawChart());
        jPanel2.add(JFreeChartUser3.drawChart());
        jPanel2.add(JFreeChartUser4.drawChart());

        this.add(jPanel1,BorderLayout.SOUTH);
        this.add(jPanel2,BorderLayout.CENTER);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == jButton1){
           CreateAndSaveLog.createSave("操作","清除星座图1的数据");
           JFreeChartUser1.xyserie1.clear();
       }else if (e.getSource() == jButton2){
           CreateAndSaveLog.createSave("操作","清除星座图2的数据");
           JFreeChartUser2.xyserie1.clear();
       }else if (e.getSource() == jButton3){
           CreateAndSaveLog.createSave("操作","清除星座图3的数据");
           JFreeChartUser3.xyserie1.clear();
       }else if (e.getSource() == jButton4){
           CreateAndSaveLog.createSave("操作","清除星座图4的数据");
           JFreeChartUser4.xyserie1.clear();
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
        }else if (e.getSource() == jButton2){
            jButton2.setForeground(new Color(0,191,255));
        }else if (e.getSource() == jButton3){
            jButton3.setForeground(new Color(0,191,255));
        }else if (e.getSource() == jButton4){
            jButton4.setForeground(new Color(0,191,255));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == jButton1){
            jButton1.setForeground(Color.white);
        }else if (e.getSource() == jButton2){
            jButton2.setForeground(Color.white);
        }else if (e.getSource() == jButton3){
            jButton3.setForeground(Color.white);
        }else if (e.getSource() == jButton4){
            jButton4.setForeground(Color.white);
        }

    }
}
