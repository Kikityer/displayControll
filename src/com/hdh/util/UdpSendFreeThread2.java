package com.hdh.util;

import com.hdh.codeFrame.server1.DataFrameser1;
import com.hdh.codeFrame.server2.DataFrameser2;
import com.hdh.page.DataPanel;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @ClassName UdpSendFreeThread2
 * @Description TODO 发送空闲帧（一旦点击启动发送按钮，后台线程开始发送空闲帧到二号服务器，二号服务器下传到终端实现入锁）
 * @Author Kikityer
 * @Date 2019/3/27 11:14
 * @Version 1.0.0
 **/
public class UdpSendFreeThread2 implements Runnable{
    /**
     * 用于给二号服务器发送空闲帧
     */
    public static boolean cancelled;

    DataFrameser2 dataFrameser2 = new DataFrameser2();

    DatagramPacket packet = null;
    DatagramSocket client = null;

    String host ;
    int port ;

    @Override
    public void run() {
        while (!cancelled){
            host = DataPanel.sever2Ip;
            port = Integer.parseInt(DataPanel.sever2Port);
            try{
                byte[][] sendFree = dataFrameser2.freePack();
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
