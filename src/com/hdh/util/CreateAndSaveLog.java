package com.hdh.util;

import com.hdh.page.LogPanel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName CreateAndSaveLog
 * @Description TODO  生成日志在日志区展示并将日志持久化到本地
 * @Author Kikityer
 * @Date 2019/3/21 14:56
 * @Version 1.0.0
 **/
public class CreateAndSaveLog {

    public static void createSave(String type,String content){
        //产生一次操作日志
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String time = simpleDateFormat.format(currentTime.getTime());
        String[] logData = {time,type,content};
        LogPanel.limitedQueue.add(logData);
        int count = 1;
        int rowNum = LogPanel.limitedQueue.size();
        for (String[] strings : LogPanel.limitedQueue){
            LogPanel.table.setValueAt(String.valueOf(count),rowNum-1,0);
            LogPanel.table.setValueAt(strings[0],rowNum-1,1);
            LogPanel.table.setValueAt(strings[1],rowNum-1,2);
            LogPanel.table.setValueAt(strings[2],rowNum-1,3);
            count++;
            rowNum--;
        }

        //将日志操作写入本地
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(time+"\\"+type+"\\"+content+"\r\n");
        byte[] bytes = stringBuffer.toString().getBytes();
        String dir = System.getProperty("user.dir");
        String path = dir+"\\"+"logCache.txt";
        File file = new File(path);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()){
            fileParent.mkdirs();
        }
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file,true);  //保存的日志信息会添加在之前保存的后面，不会覆盖之前的内容
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
