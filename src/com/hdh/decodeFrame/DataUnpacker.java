package com.hdh.decodeFrame;

import com.hdh.modelPanel.ReceiveDataPanel;
import com.hdh.page.DataPanel;
import com.hdh.util.ByteAdd;
import com.hdh.util.CreateAndSaveLog;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName DataUnpacker
 * @Description TODO  在数据接收线程将接收的数据放入用户缓存区之后，此线程用于处理接收到的数据
 * @Author Kikityer
 * @Date 2019/3/21 14:21
 * @Version 1.0.0
 **/
public class DataUnpacker implements Runnable{
    @Override
    public void run() {
        while (true){
            //日志区和接收面板显示的时间
            Date currentTime = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss SSS");
            String time = simpleDateFormat.format(currentTime.getTime());
            //持久化到本地的文件名
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileNameTime = simpleDateFormat1.format(currentTime.getTime());
            if (DataPanel.linkedList.size() != 0) {  //接收数据线程接收数据存放至链表中，判断此链表至少存在一个元素
                byte[] first = DataPanel.linkedList.getFirst(); //获取链表中最开始放入的数据
                int allPacks = 0;    //包总数
                int packNumber = 0;  //包编号
                int trueLen = 0;     //数据位真实长度
                allPacks = first[18] & 0xFF |
                          (first[17] & 0xFF) << 8 |
                          (first[16] & 0xFF) << 16 |
                          (first[15] & 0xFF) << 24;
                packNumber = first[22] & 0xFF |
                            (first[21] & 0xFF) << 8 |
                            (first[20] & 0xFF) << 16 |
                            (first[19] & 0xFF) << 24;
                byte a = 0x00;
                byte b = 0x00;
                trueLen =  first[24] & 0xFF |
                          (first[23] & 0xFF) << 8 |
                          (b & 0xFF) << 16 |
                          (a & 0xFF) << 24;
                /**
                 * 将接收到的数据在不验证正确性的情况下，直接显示于接收数据面板
                 */
                ReceiveDataPanel.jTextArea.append(time+"\n");
                byte[] by = new byte[2037];
                for (int i = 0;i < trueLen;i++){
                    by[i] = first[i+25];
                }
                for (int j = 0;j < by.length; j++){
                    String hex = Integer.toHexString(by[j] & 0xFF);
                    if (hex.length() == 1){
                        hex = '0' + hex;
                    }
                    StringBuilder stringBuilder = new StringBuilder(hex.toUpperCase());
                    stringBuilder.append(" ");
                    ReceiveDataPanel.jTextArea.append(stringBuilder.toString());
                }
                ReceiveDataPanel.jTextArea.append("\r\n"+"\r\n");

                DataPanel.linkedList.removeFirst();  //将链表头部刚刚提取出来的字节数组删除

                byte sum = 0;
                for (int i = 0; i < first.length - 3; i++) {    //校验和的正确性判断
                    sum += first[i + 2];
                }
                if (first[2062] == (byte) ~sum) {    //当校验和正确后进行解帧恢复处理
                   byte[] newData = new byte[trueLen];
                   System.arraycopy(first,25,newData,0,trueLen);
                   DataPanel.hashMap.put(packNumber,newData);

                    if (DataPanel.linkedList.size() == 0 && DataPanel.hashMap.size() > allPacks*0.9){  //链表中数据已经全部处理，集中处理hashmap
                        for (int i = 0; i < allPacks; i ++){
                            if (DataPanel.hashMap.get(i) == null){
                                DataPanel.arrayList.add(ByteAdd.add());
                            }else {
                                DataPanel.arrayList.add(DataPanel.hashMap.get(i));
                            }
                        }
                        //文件头为jpg
                        if (DataPanel.arrayList.get(0)[0] == (byte) 0xFF && DataPanel.arrayList.get(0)[1] == (byte) 0xD8 && DataPanel.arrayList.get(0)[2] == (byte) 0xFF){
                            try {
                                String dir = System.getProperty("user.dir");
                                String path = dir + "\\接收数据\\图片\\" + fileNameTime + ".jpg";
                                File file = new File(path);
                                File fileParent = file.getParentFile();
                                if (!fileParent.exists()) {
                                    fileParent.mkdirs();
                                }
                                file.createNewFile();
                                FileImageOutputStream imageOutput = new FileImageOutputStream(file);
                                for (int i = 0; i < DataPanel.arrayList.size(); i++){
                                    imageOutput.write(DataPanel.arrayList.get(i),0,DataPanel.arrayList.get(i).length);
                                }
                                imageOutput.flush();
                                imageOutput.close();

                                DataPanel.hashMap.clear();
                                DataPanel.arrayList.clear();
                                CreateAndSaveLog.createSave("接收文件","成功接收文件名为《"+fileNameTime+"》的文件");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        //文件头为png
                        else if (DataPanel.arrayList.get(0)[0] == (byte) 0x89 && DataPanel.arrayList.get(0)[1] == 0x50 && DataPanel.arrayList.get(0)[2] == 0x4E && DataPanel.arrayList.get(0)[3] == 0x47){
                            try {
                                String dir = System.getProperty("user.dir");
                                String path = dir + "\\接收数据\\图片\\" + fileNameTime + ".png";
                                File file = new File(path);
                                File fileParent = file.getParentFile();
                                if (!fileParent.exists()) {
                                    fileParent.mkdirs();
                                }
                                file.createNewFile();
                                FileImageOutputStream imageOutput = new FileImageOutputStream(file);
                                for (int i = 0; i < DataPanel.arrayList.size(); i++){
                                    imageOutput.write(DataPanel.arrayList.get(i),0,DataPanel.arrayList.get(i).length);
                                }
                                imageOutput.flush();
                                imageOutput.close();

                                DataPanel.hashMap.clear();
                                DataPanel.arrayList.clear();
                                CreateAndSaveLog.createSave("接收文件","成功接收文件名为《"+fileNameTime+"》的文件");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        //文件头为gif
                        else if (DataPanel.arrayList.get(0)[0] == 0x47 && DataPanel.arrayList.get(0)[1] == 0x49 && DataPanel.arrayList.get(0)[2] == 0x46 && DataPanel.arrayList.get(0)[3] == 0x38){
                            try {
                                String dir = System.getProperty("user.dir");
                                String path = dir + "\\接收数据\\图片\\" + fileNameTime + ".gif";
                                File file = new File(path);
                                File fileParent = file.getParentFile();
                                if (!fileParent.exists()) {
                                    fileParent.mkdirs();
                                }
                                file.createNewFile();
                                FileImageOutputStream imageOutput = new FileImageOutputStream(file);
                                for (int i = 0; i < DataPanel.arrayList.size(); i++){
                                    imageOutput.write(DataPanel.arrayList.get(i),0,DataPanel.arrayList.get(i).length);
                                }
                                imageOutput.flush();
                                imageOutput.close();

                                DataPanel.hashMap.clear();
                                DataPanel.arrayList.clear();
                                CreateAndSaveLog.createSave("接收文件","成功接收文件名为《"+fileNameTime+"》的文件");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        //文件头为TIF
                        else if (DataPanel.arrayList.get(0)[0] == 0x49 && DataPanel.arrayList.get(0)[1] == 0x49 && DataPanel.arrayList.get(0)[2] == 0x2A && DataPanel.arrayList.get(0)[3] == 0x00){
                            try {
                                String dir = System.getProperty("user.dir");
                                String path = dir + "\\接收数据\\图片\\" + fileNameTime + ".tif";
                                File file = new File(path);
                                File fileParent = file.getParentFile();
                                if (!fileParent.exists()) {
                                    fileParent.mkdirs();
                                }
                                file.createNewFile();
                                FileImageOutputStream imageOutput = new FileImageOutputStream(file);
                                for (int i = 0; i < DataPanel.arrayList.size(); i++){
                                    imageOutput.write(DataPanel.arrayList.get(i),0,DataPanel.arrayList.get(i).length);
                                }
                                imageOutput.flush();
                                imageOutput.close();

                                DataPanel.hashMap.clear();
                                DataPanel.arrayList.clear();
                                CreateAndSaveLog.createSave("接收文件","成功接收文件名为《"+fileNameTime+"》的文件");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        //文件头为BMP
                        else if (DataPanel.arrayList.get(0)[0] == 0x42 && DataPanel.arrayList.get(0)[1] == 0x4D){
                            try {
                                String dir = System.getProperty("user.dir");
                                String path = dir + "\\接收数据\\图片\\" + fileNameTime + ".bmp";
                                File file = new File(path);
                                File fileParent = file.getParentFile();
                                if (!fileParent.exists()) {
                                    fileParent.mkdirs();
                                }
                                file.createNewFile();
                                FileImageOutputStream imageOutput = new FileImageOutputStream(file);
                                for (int i = 0; i < DataPanel.arrayList.size(); i++){
                                    imageOutput.write(DataPanel.arrayList.get(i),0,DataPanel.arrayList.get(i).length);
                                }
                                imageOutput.flush();
                                imageOutput.close();

                                DataPanel.hashMap.clear();
                                DataPanel.arrayList.clear();
                                CreateAndSaveLog.createSave("接收文件","成功接收文件名为《"+fileNameTime+"》的文件");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        //文本文件
                        else {
                            try {
                                String dir = System.getProperty("user.dir");
                                String path = dir+"\\接收数据\\文本\\"+fileNameTime+".txt";
                                File file = new File(path);
                                File fileParent = file.getParentFile();
                                if (!fileParent.exists()) {
                                    fileParent.mkdirs();
                                }
                                file.createNewFile();
                                FileImageOutputStream imageOutput = new FileImageOutputStream(file);
                                FileOutputStream fos = new FileOutputStream(file);
                                for (int i = 0; i < DataPanel.arrayList.size(); i++){
                                    fos.write(DataPanel.arrayList.get(i),0,DataPanel.arrayList.get(i).length);
                                }
                                imageOutput.flush();
                                imageOutput.close();

                                DataPanel.hashMap.clear();
                                DataPanel.arrayList.clear();
                                CreateAndSaveLog.createSave("接收文件","成功接收文件名为《"+fileNameTime+"》的文件");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }else if (DataPanel.linkedList.size() == 0 && DataPanel.hashMap.size() <= allPacks*0.9){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(DataPanel.linkedList.size() == 0 && DataPanel.hashMap.size() <= allPacks*0.9){
                            DataPanel.hashMap.clear();
                        }
                    }
                }else {
                    CreateAndSaveLog.createSave("错误","文件包校验错误，此文件为无效文件");
                }

            }
        }
    }
}
