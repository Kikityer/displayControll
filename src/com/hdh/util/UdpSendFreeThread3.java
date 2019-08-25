package com.hdh.util;

import com.hdh.codeFrame.server1.DataFrameser1;
import com.hdh.codeFrame.server3.DataFrameser3;
import com.hdh.page.DataPanel;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @ClassName UdpSendFreeThread3
 * @Description TODO 发送空闲帧（一旦点击启动发送按钮，后台线程开始发送空闲帧到三号服务器，三号服务器下传到终端实现入锁）
 * @Author Kikityer
 * @Date 2019/3/27 11:14
 * @Version 1.0.0
 **/
public class UdpSendFreeThread3 implements Runnable{
    /**
     * 用于给三号服务器发送空闲帧
     */
    public static boolean cancelled;

    DataFrameser3 dataFrameser3 = new DataFrameser3();

    DatagramPacket packet = null;
    DatagramSocket client = null;

    String host ;
    int port ;

    @Override
    public void run() {
        while (!cancelled){
            host = DataPanel.sever3Ip;
            port = Integer.parseInt(DataPanel.sever3Port);
            try{
                byte[][] sendFree = dataFrameser3.freePack();
                packet = new DatagramPacket(sendFree[0], sendFree[0].length, new InetSocketAddress(host, port));
                //发送
                client.send(packet);
                //释放资源
                Thread.sleep(1000);
            }catch ( Exception e){
                e.printStackTrace();
            }
        }
    }

    public void sercerInit(){
        try {
            cancelled = false;
            client = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
    public void closeServer(){
        if (!cancelled){
            cancelled = true;
            client.close();
        }
    }
}
