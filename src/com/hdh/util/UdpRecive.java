package com.hdh.util;

import com.hdh.decodeFrame.ConditionFrame;
import com.hdh.jfreeChart.JFreeChartUser1;
import com.hdh.jfreeChart.JFreeChartUser2;
import com.hdh.jfreeChart.JFreeChartUser3;
import com.hdh.jfreeChart.JFreeChartUser4;
import com.hdh.page.DataPanel;
import com.hdh.page.StarPicPanel;
import com.hdh.rendererAndEditor.IconRenderer1;
import com.hdh.rendererAndEditor.IconRenderer2;
import com.hdh.rendererAndEditor.IconRenderer3;
import com.hdh.rendererAndEditor.IconRenderer4;
import org.jvnet.substance.SubstanceTableUI;

import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @ClassName UdpRecive
 * @Description TODO  接收任务，作为参数传入到线程中（接收链路指示帧和状态帧）
 * @Author Kikityer
 * @Date 2019/3/21 14:57
 * @Version 1.0.0
 **/
public class UdpRecive implements Runnable{


    public static boolean cancelled;

    DatagramSocket server = null;

    /**
     * 接收的数据帧类型为：链路指示帧、状态帧
     * getData()  返回数据缓冲区
     * getLength() 返回接收到数据的长度
     */
    @Override
    public void run() {
        try {
            while (!cancelled) {
                //准备接收数据 同样需要定义一个字节数组 work
                byte[] container = new byte[16400];
                DatagramPacket packet = new DatagramPacket(container, container.length);
                //接收数据
                server.receive(packet);//阻塞方法
                //分析数据
                byte[] data = packet.getData();//获得客户端传输数据的缓冲

                /**
                 * 当接收的帧结构为链路指示帧***
                 */
                if (data[13] == 0x00) {  //label判断为链路指示帧结构
                    byte sum = 0;
                    for (int i = 0; i < packet.getLength()-3; i++){   //求校验和进行数据校验，如果校验和相等则表示接收到的指示帧正确
                        sum += data[i+2];
                    }
                    if (data[14] == ~sum) {
                        /**
                         * *****接收到链路指示帧则改变为绿色，增加一个计时器 超过3秒钟则认为链路停断******后续完成
                         */
                        if (data[5] == 0x01){ //一号服务器
                            DataPanel.table.getColumn("链路指示").setCellRenderer(new IconRenderer1());
                            DataPanel.table.repaint();
                            DataPanel.lastTime1 = System.currentTimeMillis();
                            StarPicPanel.linkFlag1 = 1;
                        }else if (data[5] == 0x02){  //二号服务器
                            DataPanel.table.getColumn("链路指示").setCellRenderer(new IconRenderer2());
                            DataPanel.table.repaint();
                            DataPanel.lastTime2 = System.currentTimeMillis();
                            StarPicPanel.linkFlag2 = 2;
                        }else if (data[5] == 0x03){  //三号服务器
                            DataPanel.table.getColumn("链路指示").setCellRenderer(new IconRenderer3());
                            DataPanel.table.repaint();
                            DataPanel.lastTime3 = System.currentTimeMillis();
                            StarPicPanel.linkFlag3 = 3;
                        }else if (data[5] == 0x04){  //四号服务器
                            DataPanel.table.getColumn("链路指示").setCellRenderer(new IconRenderer4());
                            DataPanel.table.repaint();
                            DataPanel.lastTime4 = System.currentTimeMillis();
                            StarPicPanel.linkFlag4 = 4;
                        }
                        packet.setLength(container.length);
                    }
                }

                /**
                 * 当接收到的帧结构为状态帧时****
                 * 注意！！！后续需要做校验和
                 */
                if (data[13] == 0x08){     //label判断为状态帧
                    byte sum = 0;
                    for (int i = 0; i < packet.getLength()-3 ; i++){
                        sum += data[i+2];
                    }
                    if (data[16399] == ~sum){  //校验和判断正确
                        ConditionFrame.conditionInfoUnpack(data[5],data[14]);  //状态位灯的变化显示
                        DataPanel.table.repaint();

                        //绘制星图*********后续完成

                        //x轴字节数组
                        byte[] xbyte = new byte[8192];
                        //y轴字节数组
                        byte[] ybyte = new byte[8192];
                        for (int i = 0; i < 4096; i++){
                            System.arraycopy(data,15+i*8,xbyte,i*4,4);
                            System.arraycopy(data,19+i*8,ybyte,i*4,4);
                        }
                        float[] xfloat = new float[2048];
                        float[] yfloat = new float[2048];
                        if (data[5] == 0x01){  //一号服务器
                            JFreeChartUser1.xyserie1.clear();
                            for (int i = 0;i < 2048;i++){
                                byte[] x4byte = new byte[4];
                                byte[] y4byte = new byte[4];
                                System.arraycopy(xbyte,i*4,x4byte,0,4);
                                System.arraycopy(ybyte,i*4,y4byte,0,4);
                                xfloat[i] = ToByte.byteToFloat(x4byte);
                                yfloat[i] = ToByte.byteToFloat(y4byte);
                                JFreeChartUser1.xyserie1.add(xfloat[i],yfloat[i]);
                            }
                        }else if (data[5] == 0x02){  //二号服务器
                            JFreeChartUser2.xyserie1.clear();
                            for (int i = 0;i < 2048;i++){
                                byte[] x4byte = new byte[4];
                                byte[] y4byte = new byte[4];
                                System.arraycopy(xbyte,i*4,x4byte,0,4);
                                System.arraycopy(ybyte,i*4,y4byte,0,4);
                                xfloat[i] = ToByte.byteToFloat(x4byte);
                                yfloat[i] = ToByte.byteToFloat(y4byte);
                                JFreeChartUser2.xyserie1.add(xfloat[i],yfloat[i]);
                            }
                        }else if (data[5] == 0x03){  //三号服务器
                            JFreeChartUser3.xyserie1.clear();
                            for (int i = 0;i < 2048;i++){
                                byte[] x4byte = new byte[4];
                                byte[] y4byte = new byte[4];
                                System.arraycopy(xbyte,i*4,x4byte,0,4);
                                System.arraycopy(ybyte,i*4,y4byte,0,4);
                                xfloat[i] = ToByte.byteToFloat(x4byte);
                                yfloat[i] = ToByte.byteToFloat(y4byte);
                                JFreeChartUser3.xyserie1.add(xfloat[i],yfloat[i]);
                            }
                        }else {  //四号服务器
                            JFreeChartUser4.xyserie1.clear();
                            for (int i = 0;i < 2048;i++){
                                byte[] x4byte = new byte[4];
                                byte[] y4byte = new byte[4];
                                System.arraycopy(xbyte,i*4,x4byte,0,4);
                                System.arraycopy(ybyte,i*4,y4byte,0,4);
                                xfloat[i] = ToByte.byteToFloat(x4byte);
                                yfloat[i] = ToByte.byteToFloat(y4byte);
                                JFreeChartUser4.xyserie1.add(xfloat[i],yfloat[i]);
                            }
                        }
                        packet.setLength(container.length);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sercerInit() {
        try {
            cancelled = false;
            server = new DatagramSocket(9999);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
