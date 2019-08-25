package com.hdh.util;

/**
 * @ClassName ByteAdd
 * @Description TODO
 * @Author Kikityer
 * @Date 2019/3/28 12:20
 * @Version 1.0.0
 **/
public class ByteAdd {
    /**
     * 当数据包中缺少包是就将缺少的编号处补0
     */
    public static byte[] add(){
        byte[] bytes = new byte[2037];
        for (int i = 0; i < 2037;i++){
            bytes[i] = 0x00;
        }
        return bytes;
    }
}
