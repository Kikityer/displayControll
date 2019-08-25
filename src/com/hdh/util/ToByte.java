package com.hdh.util;

import java.nio.ByteBuffer;

/**
 * @ClassName ToByte
 * @Description TODO
 * @Author Kikityer
 * @Date 2019/3/21 14:57
 * @Version 1.0.0
 **/
public class ToByte {

    private static ByteBuffer buffer = ByteBuffer.allocate(8);

    /*short转byte数组*/
    public byte[] shortToByte(byte b[],short s){
        b[0] = (byte) (s >> 8);
        b[1] = (byte) (s >> 0);
        return b;
    }

    /*int转byte数组*/
    public byte[] intToByte(int val){
        byte[] b = new byte[4];
        b[3] = (byte)(val & 0xff);
        b[2] = (byte)((val >> 8) & 0xff);
        b[1] = (byte)((val >> 16) & 0xff);
        b[0] = (byte)((val >> 24) & 0xff);
        return b;
    }

    /*long转byte数组*/
    public byte[] longToByte(long x){
        buffer.putLong(0, x);
        return buffer.array();
    }

    /*byte数组转float*/
    public static float byteToFloat(byte[] b){
        int accum = 0;
        accum = accum | (b[0] & 0xff);
        accum = accum | (b[1] & 0xff) << 8;
        accum = accum | (b[2] & 0xff) << 16;
        accum = accum | (b[3] & 0xff) << 24;
        return Float.intBitsToFloat(accum);
    }

    /* byte数组转int */
    public static int byteToInt(byte[] b){
        return   b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }
}
