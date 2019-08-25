package com.hdh.codeFrame.server3;

import com.hdh.util.ToByte;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ControlFrame3ser1
 * @Description TODO   服务器3  控制帧结构的组帧（开始接收控制指令）
 * @Author Kikityer
 * @Date 2019/3/21 13:58
 * @Version 1.0.0
 **/
public class ControlFrame3ser3 {
    /**
     * 帧头中SrcID 表示信号处理服务器至显控发送数据。显控组帧时默认是0
     * 帧头中DesID 表示显控至信号处理服务器发送数据，1：一号服务器  2：二号服务器  3：三号服务器  4：四号服务器
     */
    short frameLen;  //帧头到校验位的长度 0-ffff之间  2字节
    static byte[] srcId = {0x00,0x00};   //默认为0           2字节
    static byte[] desId = {0x00,0x03};   //显控向服务器发 1表示一号服务器 ，2表示二服务器 。。  2字节
    int time;       //当前时刻到当天凌晨的毫秒数     4字节
    static byte[] label = new byte[]{0x00,0x02};  //数据帧标志位   2字节

    ToByte toByte = new ToByte();

    public byte[][] controllPack3() throws ParseException {

        byte[][] allData = new byte[1][17];   //装载所有数据
        byte[] dataHead = new byte[12];  //装载帧头

        frameLen = (short) (15);
        byte[] shortSave = new byte[2];
        toByte.shortToByte(shortSave,frameLen);  //返回两个字节数组

        long nowTime = System.currentTimeMillis();  //当前时间:单位是毫秒
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(nowTime));
        long oldTime = date.getTime();  //单位是毫秒
        time = (int) (nowTime - oldTime);   //当前时间距离当天凌晨的毫秒数

        //帧头数据的组装
        System.arraycopy(toByte.shortToByte(shortSave,frameLen),0,dataHead,0,2);
        System.arraycopy(srcId,0,dataHead,2,2);
        System.arraycopy(desId,0,dataHead,4,2);
        System.arraycopy(toByte.intToByte(time),0,dataHead,6,4);
        System.arraycopy(label,0,dataHead,10,2);

        byte[] mark = new byte[]{(byte) 0xff, (byte) 0xff};   //ffff标志位
        byte[] data = new byte[]{0x00,0x08};    //开始接收帧结构
        byte sum = 0;
        for (int i = 0; i < dataHead.length;i++){
            sum += dataHead[i];
        }
        for (int i = 0; i < data.length;i++){
            sum += data[i];
        }

        System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
        System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
        System.arraycopy(data,0,allData[0],14,2);  //数据位加入到数组
        allData[0][16] = (byte) ~sum;

        return allData;
    }
}
