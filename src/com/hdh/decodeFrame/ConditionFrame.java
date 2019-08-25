package com.hdh.decodeFrame;

import com.hdh.page.DataPanel;
import com.hdh.page.StarPicPanel;
import com.hdh.rendererAndEditor.*;

/**
 * @ClassName ConditionFrame
 * @Description TODO    接收信号服务器发送的状态帧  将一个字节的状态信息解帧以实现状态灯的显示情况
 * @Author Kikityer
 * @Date 2019/3/21 14:18
 * @Version 1.0.0
 **/
public class ConditionFrame {
    /**
     *    变量               说明
     *    Bit0      捕获标志：0未捕获；1：捕获
     *    Bit1      载同步标志：0未同步；1：同步
     *    Bit2      码同步标志：0未同步；1：同步
     *    Bit3      符号同步标志：0未同步；1：同步
     *    Bit4      帧同步标志：0未同步；1：同步
     *
     * 一共存在的情况  转换成十六进制为：  31种情况
     * 00  01  02  04  08  10  03  05  09  06  0a  12  0c  14  18  07  0b
     * 0d  0e  13  15  16  19  1a  1c  0f  17  1b  1d  1e  1f
     */
    public static void conditionInfoUnpack(byte SrcID,byte conditionInfo){
        /**
         * 判断服务器发送至显控SrcID的值
         * 若为1 则为服务器一
         * 若为2 则为服务器二
         * 若为3 则为服务器三
         * 若为4 则为服务器四
         */
        if (SrcID == 0x01){  //服务器一
            if (conditionInfo == 0x00){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x01){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x02){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x04){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x08){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x10){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x03){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x05){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x09){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x06){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0a){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x12){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x0c){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x14){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x18){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x07){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0b){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0d){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0e){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x13){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x15){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x16){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x19){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x1a){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x1c){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x0f){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x17){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x1b){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x1d){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x1e){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }else if (conditionInfo == 0x1f){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer1());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer1());
            }
            StarPicPanel.conditionFlag1 = 1;
            DataPanel.conditionFrameLastTime1 = System.currentTimeMillis();
        }else if (SrcID == 0x02){   //服务器二
            if (conditionInfo == 0x00){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x01){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x02){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x04){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x08){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x10){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x03){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x05){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x09){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x06){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0a){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x12){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x0c){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x14){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x18){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x07){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0b){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0d){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0e){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x13){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x15){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x16){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x19){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x1a){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x1c){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x0f){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x17){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x1b){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x1d){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x1e){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }else if (conditionInfo == 0x1f){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer2());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer2());
            }
            StarPicPanel.conditionFlag2 = 2;
            DataPanel.conditionFrameLastTime2 = System.currentTimeMillis();
        }else if (SrcID == 0x03){ //服务器三
            if (conditionInfo == 0x00){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x01){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x02){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x04){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x08){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x10){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x03){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x05){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x09){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x06){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0a){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x12){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x0c){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x14){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x18){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x07){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0b){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0d){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0e){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x13){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x15){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x16){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x19){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x1a){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x1c){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x0f){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x17){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x1b){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x1d){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x1e){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }else if (conditionInfo == 0x1f){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer3());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer3());
            }
            StarPicPanel.conditionFlag3 = 3;
            DataPanel.conditionFrameLastTime3 = System.currentTimeMillis();
        }else if (SrcID == 0x04){ //服务器4
            if (conditionInfo == 0x00){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x01){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x02){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x04){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x08){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x10){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x03){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x05){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x09){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x06){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0a){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x12){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x0c){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x14){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x18){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x07){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0b){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0d){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x0e){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x13){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x15){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x16){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x19){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x1a){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x1c){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x0f){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer());
            }else if (conditionInfo == 0x17){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x1b){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x1d){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x1e){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }else if (conditionInfo == 0x1f){
                DataPanel.table.getColumn("捕获").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("载同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("码同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("符号同步").setCellRenderer(new IconRenderer4());
                DataPanel.table.getColumn("帧同步").setCellRenderer(new IconRenderer4());
            }
            StarPicPanel.conditionFlag4 = 4;
            DataPanel.conditionFrameLastTime4 = System.currentTimeMillis();
        }
    }
}
