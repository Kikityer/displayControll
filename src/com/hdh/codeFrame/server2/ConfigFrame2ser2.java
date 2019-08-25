package com.hdh.codeFrame.server2;

import com.hdh.util.ToByte;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ConfigFrame2ser1
 * @Description TODO   服务器2   接收模式下配置帧的组帧******接收模式*****
 * @Author Kikityer
 * @Date 2019/3/21 13:57
 * @Version 1.0.0
 **/
public class ConfigFrame2ser2 {
    /**
     * 配置帧结构体变量：
     * para_type:参数类型  1：发射  2：接收
     * mod_type：调制方式选择  0：扩频  1：BPSK
     * m1_gen：M序列1的生成多项式
     * m1_init：M序列1的初始状态
     * m2_gen：M序列2的生成多项式
     * m2_init：M序列2的初始状态
     * ecc_type：纠错码类型  0：无编码 1：卷积+RS码 2：LDPC码
     * data_tate：数据速率：10——4000
     * rf_fac：滚降系数 0：0，35    1：0.6     2：1
     * slot_time: 时隙   4-10
     */


    /**
     * *****注意：增加时隙这个参数，配置帧数据为变为20字节
     * 帧同步头    帧头     数据     校验和
     *  2字节    12字节   20字节     1字节
     *
     * 帧头中SrcID 表示信号处理服务器至显控发送数据。显控组帧时默认是0
     * 帧头中DesID 表示显控至信号处理服务器发送数据，1：一号服务器  2：二号服务器  3：三号服务器  4：四号服务器
     */
    byte[] mark = new byte[]{(byte) 0xff, (byte) 0xff};   //帧同步头  0xFF  0xFF

    short frameLen;  //帧头到校验位的长度 0-ffff之间  2字节
    static byte[] srcId = {0x00,0x00};   //默认为0           2字节
    static byte[] desId = {0x00,0x02};   //显控向服务器发 1表示一号服务器 ，2表示二服务器 。。。            2字节
    int time;       //2000年01月01号 00：00：00到现在的总毫秒数     6字节
    static byte[] label = new byte[]{0x00,0x04};  //数据帧标志位   2字节
    ToByte toByte = new ToByte();

    public byte[][] configPack(String mod_type,String m1_gen,String m1_init,String m2_gen,String m2_init,String ecc_type,String data_rate,String rf_fac,String slot_time) throws ParseException {

        byte[][] allData = new byte[1][35];   //装载所有数据
        byte[] dataHead = new byte[12];  //装载帧头
        byte[] data = new byte[20];  //用于装载数据位

        frameLen = (short) (33);
        byte[] shortSave = new byte[2];

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


        byte[] m1_genSave = new byte[2];
        toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen));  //返回两个字节M序列1的生成多项式数组
        byte[] m1_initSave = new byte[2];
        toByte.shortToByte(m1_initSave,Short.parseShort(m1_init));  //返回两个字节M序列1的初始状态数组
        byte[] m2_genSave = new byte[2];
        toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen));  //返回两个字节M序列1的生成多项式数组
        byte[] m2_initSave = new byte[2];
        toByte.shortToByte(m2_initSave,Short.parseShort(m2_init));  //返回两个字节M序列1的初始状态数组
        byte[] data_rateSave = new byte[2];
        toByte.shortToByte(data_rateSave,Short.parseShort(data_rate));  //返回两个字节速率数组
        byte[] slot_timeSave = new byte[2];
        toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time));  //返回两个字节时隙数组

        if (mod_type.equals("BPSK") && ecc_type.equals("无编码") && rf_fac.equals("0.35")){  //情况1
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x01; //mod_type  BPSK
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x00; //ecc_type  无编码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x00; //rf_fac  0.35
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组

        }else if (mod_type.equals("BPSK") && ecc_type.equals("无编码") && rf_fac.equals("0.6")){ //情况2
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x01; //mod_type  BPSK
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x00; //ecc_type 无编码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x01; //rf_fac  0.6
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("BPSK") && ecc_type.equals("无编码") && rf_fac.equals("1")){ //情况3
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x01; //mod_type  BPSK
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x00; //ecc_type 无编码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x02; //rf_fac  0.6
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("BPSK") && ecc_type.equals("卷积+RS码") && rf_fac.equals("0.35")){  //情况4
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x01; //mod_type  BPSK
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x01; //ecc_type  卷积+RS码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x00; //rf_fac  0.35
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("BPSK") && ecc_type.equals("卷积+RS码") && rf_fac.equals("0.6")){ //情况5
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x01; //mod_type  BPSK
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x01; //ecc_type  卷积+RS码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x01; //rf_fac  0.6
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("BPSK") && ecc_type.equals("卷积+RS码") && rf_fac.equals("1")){  //情况6
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x01; //mod_type  BPSK
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x01; //ecc_type  卷积+RS码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x02; //rf_fac  1
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("BPSK") && ecc_type.equals("LDPC码") && rf_fac.equals("0.35")){ //情况7
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x01; //mod_type  BPSK
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x02; //ecc_type  LDPC码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x00; //rf_fac  0.35
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("BPSK") && ecc_type.equals("LDPC码") && rf_fac.equals("0.6")){ //情况8
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x01; //mod_type  BPSK
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x02; //ecc_type  LDPC码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x01; //rf_fac  0.6
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("BPSK") && ecc_type.equals("LDPC码") && rf_fac.equals("1")){  //情况9
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x01; //mod_type  BPSK
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x02; //ecc_type  LDPC码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x02; //rf_fac  1
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("扩频") && ecc_type.equals("无编码") && rf_fac.equals("0.35")){ //情况10
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x00; //mod_type  扩频
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x00; //ecc_type  无编码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x00; //rf_fac  0.35
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("扩频") && ecc_type.equals("无编码") && rf_fac.equals("0.6")){ //情况11
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x00; //mod_type  扩频
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x00; //ecc_type  无编码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x01; //rf_fac  0.6
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("扩频") && ecc_type.equals("无编码") && rf_fac.equals("1")){ //情况12
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x00; //mod_type  扩频
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x00; //ecc_type  无编码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x02; //rf_fac  1
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("扩频") && ecc_type.equals("卷积+RS码") && rf_fac.equals("0.35")){ //情况13
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x00; //mod_type  扩频
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x01; //ecc_type  卷积+RS码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x00; //rf_fac  0.35
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("扩频") && ecc_type.equals("卷积+RS码") && rf_fac.equals("0.6")){ //情况14
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x00; //mod_type  扩频
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x01; //ecc_type  卷积+RS码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x01; //rf_fac  0.6
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("扩频") && ecc_type.equals("卷积+RS码") && rf_fac.equals("1")){ //情况15
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x00; //mod_type  扩频
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x01; //ecc_type  卷积+RS码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x02; //rf_fac  1
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("扩频") && ecc_type.equals("LDPC码") && rf_fac.equals("0.35")){ //情况16
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x00; //mod_type  扩频
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x02; //ecc_type  LDPC码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x00; //rf_fac  0.35
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("扩频") && ecc_type.equals("LDPC码") && rf_fac.equals("0.6")){ //情况17
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x00; //mod_type  扩频
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x02; //ecc_type  LDPC码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x01; //rf_fac  0.6
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }else if (mod_type.equals("扩频") && ecc_type.equals("LDPC码") && rf_fac.equals("1")){ //情况18
            data[0] = 0x00;
            data[1] = 0x02; //para_type 接收参数
            data[2] = 0x00;
            data[3] = 0x00; //mod_type  扩频
            System.arraycopy(toByte.shortToByte(m1_genSave,Short.parseShort(m1_gen)),0,data,4,2);
            System.arraycopy(toByte.shortToByte(m1_initSave,Short.parseShort(m1_init)),0,data,6,2);
            System.arraycopy(toByte.shortToByte(m2_genSave,Short.parseShort(m2_gen)),0,data,8,2);
            System.arraycopy(toByte.shortToByte(m2_initSave,Short.parseShort(m2_init)),0,data,10,2);
            data[12] = 0x00;
            data[13] = 0x02; //ecc_type  LDPC码
            System.arraycopy(toByte.shortToByte(data_rateSave,Short.parseShort(data_rate)),0,data,14,2);
            data[16] = 0x00;
            data[17] = 0x02; //rf_fac  1
            System.arraycopy(toByte.shortToByte(slot_timeSave,Short.parseShort(slot_time)),0,data,18,2);

            byte sum = 0;  //校验和
            for (int i = 0; i < dataHead.length;i++){
                sum += dataHead[i];
            }
            for (int i = 0; i < data.length;i++){
                sum += data[i];
            }

            //整个配置帧的组装
            System.arraycopy(mark,0,allData[0],0,2);   //ffff标志位加入到数组
            System.arraycopy(dataHead,0,allData[0],2,12);  //帧头加入到数组
            System.arraycopy(data,0,allData[0],14,20);  //数据位加入到数组
            allData[0][34] = (byte) ~sum;  //校验和加入数组
        }
        return allData;
    }
}
