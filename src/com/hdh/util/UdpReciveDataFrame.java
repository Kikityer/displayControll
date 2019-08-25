package com.hdh.util;

import com.hdh.page.DataPanel;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @ClassName UdpReciveDataFrame
 * @Description TODO  用于只接收数据帧包任务（单独开启一个线程用于接收数据帧）
 * @Author Kikityer
 * @Date 2019/3/21 14:58
 * @Version 1.0.0
 **/
public class UdpReciveDataFrame implements Runnable{
    public static boolean cancelled;

    DatagramSocket server = null;
    int i = 0;


    /**
     * data数组就是我们需要接收的数据，固定长度为2063
     * 接收的数据帧类型为：数据帧（包括文本和图片）
     * getData()  返回数据缓冲区
     * getLength() 返回接收到数据的长度
     *
     * *****在此线程中只接收发送端发送的数据（即将接收的数据直接放入线程数据共享缓冲区）******
     * *****后续的解包等一系列动作放在另外一个线程进行，减少接收损耗时间，尽量减少丢包*****
     */
    @Override
    public void run() {
        try {
            while (!cancelled) {
                byte[] container = new byte[2063];
                //用于接收数据帧的数据包（数据包的长度为container 的长度）
                DatagramPacket packet = new DatagramPacket(container, container.length);
                //接收数据
                server.receive(packet);//阻塞方法
                //分析数据
                byte[] data = packet.getData();//获得客户端传输数据的缓冲

                if (data[13] == 0x01){     //label判断为数据帧结构

                    if (data[14] == 0x55){
                        DataPanel.linkedList.add(data);
                        System.out.println( 111);
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
            server = new DatagramSocket(8888);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
