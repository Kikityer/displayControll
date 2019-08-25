package com.hdh.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @ClassName UdpSend
 * @Description TODO   udp发送函数（发送配置帧、控制帧、数据帧（非空闲））
 * @Author Kikityer
 * @Date 2019/3/21 14:59
 * @Version 1.0.0
 **/
public class UdpSend {

    DatagramSocket client = null;
    DatagramPacket packet = null;

    public void sendmsg(byte[][] data, String host, Integer port){
        try {
            client = new DatagramSocket();
            for (int i = 0; i < data.length;i++){
                //将数据打包 DatagramPacket + 服务器地址及端口号 来完成打包
                packet = new DatagramPacket(data[i], data[i].length, new InetSocketAddress(host, port));
                //发送
                client.send(packet);
                Thread.sleep(10); //发送每个包的间隔为十毫秒  优化udp丢包问题
            }
            //释放资源
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
