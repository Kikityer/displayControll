package com.hdh;

import com.hdh.decodeFrame.DataUnpacker;
import com.hdh.page.DataPanel;
import com.hdh.page.StarPicPanel;
import com.hdh.page.WindowInitial;
import com.hdh.rendererAndEditor.*;
import com.hdh.util.UdpRecive;
import com.hdh.util.UdpReciveDataFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.*;
import org.jvnet.substance.theme.SubstanceLightAquaTheme;
import org.jvnet.substance.theme.SubstanceSteelBlueTheme;
import org.jvnet.substance.theme.SubstanceUltramarineTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import javax.swing.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName WindowTest
 * @Description TODO
 * @Author Kikityer
 * @Date 2019/3/21 13:39
 * @Version 1.0.0
 **/
public class WindowTest {


    public static long old1 = 0;
    public static long old2 = 0;
    public static long old3 = 0;
    public static long old4 = 0;
    public static long conditionOld1,conditionOld2,conditionOld3,conditionOld4;

    public static WindowInitial windowInitial;
    public static void main(String[] args){



        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {

                JDialog.setDefaultLookAndFeelDecorated(true);

                SubstanceLookAndFeel.setSkin(new OfficeBlue2007Skin()); //设置皮肤
                try {
                    windowInitial = new WindowInitial();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                SubstanceLookAndFeel.setCurrentTheme(new SubstanceSteelBlueTheme()); //设置主题
//                SubstanceLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());  //设置按钮风格
                SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark()); //设置水印

            }

        });

        /**
         * 软件开启时，udp接收线程打开（接收链路指示帧和状态帧）
         */
        UdpRecive udpRecive = new UdpRecive();
        udpRecive.sercerInit();
        Thread thread = new Thread(udpRecive);
        thread.start();

        /**
         * 软件开启时，udp接收数据帧线程打开（接收数据帧）
         */
        UdpReciveDataFrame udpReciveDataFrame = new UdpReciveDataFrame();
        udpReciveDataFrame.sercerInit();
        Thread thread1 = new Thread(udpReciveDataFrame);
        thread1.start();
        /**
         * 软件开启时，处理udp接收到的数据帧的线程开启
         */
        DataUnpacker dataUnpacker = new DataUnpacker();
        Thread thread2 = new Thread(dataUnpacker);
        thread2.start();





        /**
         * 软件启动检查链路状态和状态帧的变化情况
         *
         * 本定时器设计的思想
         * 接收线程一直在运行，并将接收到链路指示帧的时间戳作为时间点存储到DataPanel.lastTime全局变量中
         * 本定时器的线程完成的任务是；每隔3秒判断一下本此读取到的DataPanel.lastTime值（now）和上一次读取到
         * 的DataPanel.lastTime值（old）是否一致，若一致则表示在这3秒内没接收到新的链路指示帧；反之则表示正常
         * 接收到链路指示帧
         */
        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (DataPanel.lastTime1 != 0){
                    long now = DataPanel.lastTime1;
                    if (now == old1){
                        DataPanel.table.getColumn("链路指示").setCellRenderer(new IconRenderer1_1());
                        DataPanel.table.repaint();
                        StarPicPanel.linkFlag1 = 0;
                    }
                    old1 = now;
                }
                if (DataPanel.lastTime2 != 0){
                    long now = DataPanel.lastTime2;
                    if (now == old2){
                        DataPanel.table.getColumn("链路指示").setCellRenderer(new IconRenderer2_1());
                        DataPanel.table.repaint();
                        StarPicPanel.linkFlag2 = 0;
                    }
                    old2 = now;
                }
                if (DataPanel.lastTime3 != 0){
                    long now = DataPanel.lastTime3;
                    if (now == old3){
                        DataPanel.table.getColumn("链路指示").setCellRenderer(new IconRenderer3_1());
                        DataPanel.table.repaint();
                        StarPicPanel.linkFlag3 = 0;
                    }
                    old3 = now;
                }
                if (DataPanel.lastTime4 != 0){
                    long now = DataPanel.lastTime4;
                    if (now == old4){
                        DataPanel.table.getColumn("链路指示").setCellRenderer(new IconRenderer4_1());
                        DataPanel.table.repaint();
                        StarPicPanel.linkFlag4 = 0;
                    }
                    old4 = now;
                }

                if (DataPanel.conditionFrameLastTime1 != 0){
                    long now  = DataPanel.conditionFrameLastTime1;
                    if (now == conditionOld1){
                        DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1_1());
                        DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1_1());
                        DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1_1());
                        DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1_1());
                        DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1_1());
                        DataPanel.table.repaint();
                        StarPicPanel.conditionFlag1 = 0;
                    }
                    conditionOld1 = now;
                }
                if (DataPanel.conditionFrameLastTime2 != 0){
                    long now = DataPanel.conditionFrameLastTime2;
                    if (now == conditionOld2){
                        DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2_1());
                        DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2_1());
                        DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2_1());
                        DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2_1());
                        DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2_1());
                        DataPanel.table.repaint();
                        StarPicPanel.conditionFlag2 = 0;
                    }
                    conditionOld2 = now;
                }
                if (DataPanel.conditionFrameLastTime3 != 0){
                    long now = DataPanel.conditionFrameLastTime3;
                    if (now == conditionOld3){
                        DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3_1());
                        DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3_1());
                        DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3_1());
                        DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3_1());
                        DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3_1());
                        DataPanel.table.repaint();
                        StarPicPanel.conditionFlag3 = 0;
                    }
                    conditionOld3 = now;
                }
                if (DataPanel.conditionFrameLastTime4 != 0){
                    long now = DataPanel.conditionFrameLastTime4;
                    if (now == conditionOld4){
                        DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4_1());
                        DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4_1());
                        DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4_1());
                        DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4_1());
                        DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4_1());
                        DataPanel.table.repaint();
                        StarPicPanel.conditionFlag4 = 0;
                    }
                    conditionOld4 = now;
                }
            }
        },0,2500);

    }
}
