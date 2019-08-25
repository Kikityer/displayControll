package com.hdh.codeFrame.server4;

import com.hdh.util.ToByte;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DataFrameser1
 * @Description TODO   服务器4  数据帧结构的组帧(发送至信号处理服务器)
 * @Author Kikityer
 * @Date 2019/3/21 14:00
 * @Version 1.0.0
 **/
public class DataFrameser4 {
    /**
     * 帧头中SrcID 表示信号处理服务器至显控发送数据。显控组帧时默认是0
     * 帧头中DesID 表示显控至信号处理服务器发送数据，1：一号服务器  2：二号服务器  3：三号服务器  4：四号服务器
     */
    static byte[] mark = new byte[]{(byte) 0xff, (byte) 0xff};   //FFFF帧同步头        2字节
    static byte[] frameLen = new byte[]{0x08,0x0d};              //帧头到校验位的长度   2字节
    static byte srcId[] = {0x00,0x00};                           //默认为0      2字节
    static byte desId[] = {0x00,0x04};                           //一号服务器      2字节
    int time;                                                   //当前时刻到当天凌晨的总毫秒数     4字节
    static byte[] label = new byte[]{0x00,0x01};                 //数据帧标志位   2字节

    ToByte toByte = new ToByte();

    /**
     * 空闲数据帧组帧函数
     * @return
     * @throws ParseException
     */
    public byte[][] freePack() throws ParseException {

        byte[][] allData = new byte[1][2063];  //用于装载整个数据帧结构
        /**
         * 帧同步位+帧头的组装 【14个字节】
         */
        System.arraycopy(mark,0,allData[0],0,2);
        System.arraycopy(frameLen,0,allData[0],2,2);
        System.arraycopy(srcId,0,allData[0],4,2);
        System.arraycopy(desId,0,allData[0],6,2);
        long nowTime = System.currentTimeMillis();  //当前时间:单位是毫秒
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(nowTime));
        long oldTime = date.getTime();  //单位是毫秒
        time = (int) (nowTime - oldTime);   //当前时间距离当天凌晨的毫秒数
        System.arraycopy(toByte.intToByte(time),0,allData[0],8,4);
        System.arraycopy(label,0,allData[0],12,2);
        /**
         * 数据位+校验位的组装 【2049个字节】
         */
        allData[0][14] = (byte) 0xAA; //帧导头之标识符（空闲帧）
        allData[0][15] = 0x00;
        allData[0][16] = 0x00;
        allData[0][17] = 0x00;
        allData[0][18] = 0x01;        //帧导头之包总数 （四个字节）
        allData[0][19] = 0x00;
        allData[0][20] = 0x00;
        allData[0][21] = 0x00;
        allData[0][22] = 0x00;        //帧导头之包编号  （四个字节）空闲帧则编号默认为0
        allData[0][23] = 0x00;
        allData[0][24] = 0x00;       //帧导头之有效长度 （最长为2042[0xFA],最短为0[0x00]，否则为接收的实际长度）
        for (int i = 25; i < allData[0].length-1; i++){
            allData[0][i] = 0x55;
        }
        byte sum = 0;
        for (int i = 2; i < allData[0].length-1; i++){
            sum += allData[0][i];
        }
        allData[0][2062] = (byte) ~sum;

        return allData;
    }

    /**
     * 非空闲数据帧的组帧
     * @param sourceData
     * @return
     * @throws ParseException
     */
    public byte[][] dataPack(byte[] sourceData) throws ParseException {
        byte[][] allData = new byte[0][];
        /**
         * 数据源的字节数小于2037个字节
         */
        if (sourceData.length/ 2037 == 0){
            System.out.println("小于2037个字节");
            allData = new byte[1][2063]; //用于装载整个数据帧结构
            /**
             * 帧同步位+帧头的组装 【14个字节】
             */
            System.arraycopy(mark,0,allData[0],0,2);
            System.arraycopy(frameLen,0,allData[0],2,2);
            System.arraycopy(srcId,0,allData[0],4,2);
            System.arraycopy(desId,0,allData[0],6,2);
            long nowTime = System.currentTimeMillis();  //当前时间:单位是毫秒
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(simpleDateFormat.format(nowTime));
            long oldTime = date.getTime();  //单位是毫秒
            time = (int) (nowTime - oldTime);   //当前时间距离当天凌晨的毫秒数
            System.arraycopy(toByte.intToByte(time),0,allData[0],8,4);
            System.arraycopy(label,0,allData[0],12,2);
            /**
             * 数据位+校验位的组装 【2049个字节】
             */
            allData[0][14] = (byte) 0x55; //帧导头之标识符（数据帧）
            allData[0][15] = 0x00;
            allData[0][16] = 0x00;
            allData[0][17] = 0x00;
            allData[0][18] = 0x01;        //帧导头之包总数 （四个字节）
            allData[0][19] = 0x00;
            allData[0][20] = 0x00;
            allData[0][21] = 0x00;
            allData[0][22] = 0x00;        //帧导头之包编号  （四个字节）空闲帧则编号默认为0
            byte[] shortSave = new byte[2];
            System.arraycopy( toByte.shortToByte(shortSave, (short) sourceData.length),0,allData[0],23,2);//帧导头之有效长度 （最长为2037[0x07 0xf5]，否则为接收的实际长度）
            for (int i = 0; i < sourceData.length; i++){
                allData[0][i+25] = sourceData[i];
            }
            for (int i = 0; i < 2037-sourceData.length; i++){
                allData[0][i+25+sourceData.length] = 0x55;
            }
            byte sum = 0;
            for (int i = 2; i < allData[0].length-1; i++){
                sum += allData[0][i];
            }
            allData[0][2062] = (byte) ~sum;
        }
        /**
         * 数据源的字节数等于2037个字节
         */
        if (sourceData.length/2037 == 1 && sourceData.length % 2037 == 0 ){
            System.out.println("等于2037个字节");
            allData = new byte[1][2063]; //用于装载整个数据帧结构
            /**
             * 帧同步位+帧头的组装 【14个字节】
             */
            System.arraycopy(mark,0,allData[0],0,2);
            System.arraycopy(frameLen,0,allData[0],2,2);
            System.arraycopy(srcId,0,allData[0],4,2);
            System.arraycopy(desId,0,allData[0],6,2);
            long nowTime = System.currentTimeMillis();  //当前时间:单位是毫秒
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(simpleDateFormat.format(nowTime));
            long oldTime = date.getTime();  //单位是毫秒
            time = (int) (nowTime - oldTime);   //当前时间距离当天凌晨的毫秒数
            System.arraycopy(toByte.intToByte(time),0,allData[0],8,4);
            System.arraycopy(label,0,allData[0],12,2);
            /**
             * 数据位+校验位的组装 【2049个字节】
             */
            /**
             * 数据位+校验位的组装 【2049个字节】
             */
            allData[0][14] = (byte) 0x55; //帧导头之标识符（数据帧）
            allData[0][15] = 0x00;
            allData[0][16] = 0x00;
            allData[0][17] = 0x00;
            allData[0][18] = 0x01;        //帧导头之包总数 （四个字节）
            allData[0][19] = 0x00;
            allData[0][20] = 0x00;
            allData[0][21] = 0x00;
            allData[0][22] = 0x00;        //帧导头之包编号  （四个字节）空闲帧则编号默认为0
            allData[0][23] = 0x07;
            allData[0][24] = (byte) 0xF5; //帧导头之有效长度
            for (int i = 0; i < sourceData.length; i++){
                allData[0][i+25] = sourceData[i];
            }
            byte sum = 0;
            for (int i = 2; i < allData[0].length-1; i++){
                sum += allData[0][i];
            }
            allData[0][2062] = (byte) ~sum;
        }
        /**
         * 数据源的字节数大于2037个字节，但正好能被分成整数包个数
         */
        if (sourceData.length/2037 > 1 && sourceData.length % 2037 == 0 ){
            System.out.println("大于2037个字节，但正好能被分成整数包个数");
            allData = new byte[sourceData.length/2037][2063]; //用于装载整个数据帧结构
            for (int i = 0;i < sourceData.length/2037;i++){
                /**
                 * 帧同步位+帧头的组装 【14个字节】
                 */
                System.arraycopy(mark,0,allData[i],0,2);
                System.arraycopy(frameLen,0,allData[i],2,2);
                System.arraycopy(srcId,0,allData[i],4,2);
                System.arraycopy(desId,0,allData[i],6,2);
                long nowTime = System.currentTimeMillis();  //当前时间:单位是毫秒
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(simpleDateFormat.format(nowTime));
                long oldTime = date.getTime();  //单位是毫秒
                time = (int) (nowTime - oldTime);   //当前时间距离当天凌晨的毫秒数
                System.arraycopy(toByte.intToByte(time),0,allData[i],8,4);
                System.arraycopy(label,0,allData[i],12,2);
                /**
                 * 数据位+校验位的组装 【2049个字节】
                 */
                allData[i][14] = (byte) 0x55; //帧导头之标识符  （数据帧）
                System.arraycopy(toByte.intToByte(sourceData.length/2037),0,allData[i],15,4);  //包总数
                System.arraycopy(toByte.intToByte(i),0,allData[i],19,4);  //包编号
                allData[i][23] = 0x07;
                allData[i][24] = (byte) 0xF5; //帧导头之有效长度 （最长为2037[0x07 0xF5]，否则为接收的实际长度）
                for (int j = 0; j < 2037; j++){
                    allData[i][j+25] = sourceData[i*2037+j];
                }
                byte sum = 0;
                for (int k = 2; k < allData[i].length-1; k++){
                    sum += allData[i][k];
                }
                allData[i][2062] = (byte) ~sum;
            }
        }
        /**
         * 数据源的字节数大于2037个字节，不能被分成整数包个数
         */
        if (sourceData.length/2037 >= 1 && sourceData.length % 2037 != 0 ){
            System.out.println("大于2037个字节，不能被分成整数包个数");
            allData = new byte[sourceData.length / 2037 + 1][2063]; //用于装载整个数据帧结构
            for (int i = 0; i < sourceData.length / 2037 + 1; i++){
                /**
                 * 帧同步位+帧头的组装 【14个字节】
                 */
                System.arraycopy(mark,0,allData[i],0,2);
                System.arraycopy(frameLen,0,allData[i],2,2);
                System.arraycopy(srcId,0,allData[i],4,2);
                System.arraycopy(desId,0,allData[i],6,2);
                long nowTime = System.currentTimeMillis();  //当前时间:单位是毫秒
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(simpleDateFormat.format(nowTime));
                long oldTime = date.getTime();  //单位是毫秒
                time = (int) (nowTime - oldTime);   //当前时间距离当天凌晨的毫秒数
                System.arraycopy(toByte.intToByte(time),0,allData[i],8,4);
                System.arraycopy(label,0,allData[i],12,2);
                /**
                 * 数据位+校验位的组装 【2049个字节】
                 */
                allData[i][14] = (byte) 0x55; //帧导头之标识符  （数据帧）
                System.arraycopy(toByte.intToByte(sourceData.length/2037+1),0,allData[i],15,4);  //包总数
                System.arraycopy(toByte.intToByte(i),0,allData[i],19,4);  //包编号
                if (i == sourceData.length/2037){   //最后一个包的有效长度
                    byte[] shortSave1 = new byte[2];
                    System.arraycopy( toByte.shortToByte(shortSave1, (short) (sourceData.length % 2037)),0,allData[i],23,2);//帧导头之有效长度
                    for (int j = 0; j < sourceData.length % 2037; j++){
                        allData[i][j+25] = sourceData[(sourceData.length/2037)*2037+j];
                    }
                    for (int k = 0; k < 2037-sourceData.length % 2037; k++){
                        allData[i][k+25+sourceData.length % 2037] = 0x55;
                    }
                    byte sum = 0;
                    for (int m = 2; m < allData[i].length-1; m++){
                        sum += allData[i][m];
                    }
                    allData[i][2062] = (byte) ~sum;
                }else {
                    allData[i][23] = 0x07;
                    allData[i][24] = (byte) 0xF5; //帧导头之有效长度
                    for (int j = 0; j < 2037; j++){
                        allData[i][j+25] = sourceData[i*2037+j];
                    }
                    byte sum = 0;
                    for (int k = 2; k < allData[i].length-1; k++){
                        sum += allData[i][k];
                    }
                    allData[i][2062] = (byte) ~sum;
                }
            }
        }
        return allData;
    }

}
