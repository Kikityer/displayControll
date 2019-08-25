package com.hdh.rendererAndEditor;

import com.hdh.WindowTest;
import com.hdh.modelPanel.*;
import com.hdh.page.WindowInitial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @ClassName ButtonEditor
 * @Description TODO  单元格内渲染的按钮增加可编辑事件 （初始化时）
 * @Author Kikityer
 * @Date 2019/3/21 14:48
 * @Version 1.0.0
 **/
public class ButtonEditor extends DefaultCellEditor{  //触发渲染的按钮事件
    /**
     * 发单元格按钮的事件的绑定
     */
    private JPanel panel;
    private JButton button;
    private int row;
    private int column;
    String value;

    public ButtonEditor() {
        super(new JTextField());

        // 设置点击几次激活编辑。
        this.setClickCountToStart(1);
        this.initButton();
        this.initPanel();
        // 添加按钮。
        this.panel.add(this.button);
    }
    private void initButton(){
        this.button = new JButton();
        // 设置按钮的大小及位置。
        float a = (WindowInitial.dataPanelWidth)/14;
        float b = (float) ((WindowInitial.dataPanelHeight*0.8-50)/4);
        this.button.setBounds((int) (a*0.1), (int) (b*0.35), (int) (a*0.8), (int) (b*0.3));
        // 为按钮添加事件。这里只能添加ActionListner事件，Mouse事件无效。
        this.button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (value.equals("编辑")) {    //点击“编辑”按钮触发的事件 （弹出服务器配置模态框）
                    if (row == 0 && column == 13) {                 //点击（0，13）坐标的编辑按钮触发事件
                        MyDialogSetting1 myDialogSetting1 = null;
                        try {
                            myDialogSetting1 = new MyDialogSetting1(WindowTest.windowInitial);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        // 设置为模态
                        myDialogSetting1.setModal(true);
                        myDialogSetting1.setTitle("服务器1参数配置");
                        myDialogSetting1.setSize(400, 650);
                        myDialogSetting1.setResizable(false);
                        myDialogSetting1.setLocationRelativeTo(WindowTest.windowInitial);
                        LaunchParaPanel.jComboBox1.setSelectedItem(MyDialogSetting1.mod_type1);
                        LaunchParaPanel.jtf1.setText(MyDialogSetting1.m1_gen1);
                        LaunchParaPanel.jtf2.setText(MyDialogSetting1.m1_init1);
                        LaunchParaPanel.jtf3.setText(MyDialogSetting1.m2_gen1);
                        LaunchParaPanel.jtf4.setText(MyDialogSetting1.m2_init1);
                        LaunchParaPanel.jComboBox2.setSelectedItem(MyDialogSetting1.ecc_type1);
                        LaunchParaPanel.jtf5.setText(MyDialogSetting1.data_rate1);
                        LaunchParaPanel.jComboBox3.setSelectedItem(MyDialogSetting1.rf_fac1);
                        LaunchParaPanel.jtf6.setText(MyDialogSetting1.slot_time1);
                        myDialogSetting1.setVisible(true);
                    }else if (row == 1 && column == 13){            //点击（1，12）坐标的编辑按钮触发事件
                        MyDialogSetting2 myDialogSetting2 = null;
                        try {
                            myDialogSetting2 = new MyDialogSetting2(WindowTest.windowInitial);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        // 设置为模态
                        myDialogSetting2.setModal(true);
                        myDialogSetting2.setTitle("服务器2参数配置");
                        myDialogSetting2.setSize(400, 650);
                        myDialogSetting2.setResizable(false);
                        myDialogSetting2.setLocationRelativeTo(WindowTest.windowInitial);
                        LaunchParaPanel.jComboBox1.setSelectedItem(MyDialogSetting2.mod_type1);
                        LaunchParaPanel.jtf1.setText(MyDialogSetting2.m1_gen1);
                        LaunchParaPanel.jtf2.setText(MyDialogSetting2.m1_init1);
                        LaunchParaPanel.jtf3.setText(MyDialogSetting2.m2_gen1);
                        LaunchParaPanel.jtf4.setText(MyDialogSetting2.m2_init1);
                        LaunchParaPanel.jComboBox2.setSelectedItem(MyDialogSetting2.ecc_type1);
                        LaunchParaPanel.jtf5.setText(MyDialogSetting2.data_rate1);
                        LaunchParaPanel.jComboBox3.setSelectedItem(MyDialogSetting2.rf_fac1);
                        LaunchParaPanel.jtf6.setText(MyDialogSetting2.slot_time1);
                        myDialogSetting2.setVisible(true);
                    }else if (row == 2 && column == 13){            //点击（2，12）坐标的编辑按钮触发事件
                        MyDialogSetting3 myDialogSetting3 = null;
                        try {
                            myDialogSetting3 = new MyDialogSetting3(WindowTest.windowInitial);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        // 设置为模态
                        myDialogSetting3.setModal(true);
                        myDialogSetting3.setTitle("服务器3参数配置");
                        myDialogSetting3.setSize(400, 650);
                        myDialogSetting3.setResizable(false);
                        myDialogSetting3.setLocationRelativeTo(WindowTest.windowInitial);
                        LaunchParaPanel.jComboBox1.setSelectedItem(MyDialogSetting3.mod_type1);
                        LaunchParaPanel.jtf1.setText(MyDialogSetting3.m1_gen1);
                        LaunchParaPanel.jtf2.setText(MyDialogSetting3.m1_init1);
                        LaunchParaPanel.jtf3.setText(MyDialogSetting3.m2_gen1);
                        LaunchParaPanel.jtf4.setText(MyDialogSetting3.m2_init1);
                        LaunchParaPanel.jComboBox2.setSelectedItem(MyDialogSetting3.ecc_type1);
                        LaunchParaPanel.jtf5.setText(MyDialogSetting3.data_rate1);
                        LaunchParaPanel.jComboBox3.setSelectedItem(MyDialogSetting3.rf_fac1);
                        LaunchParaPanel.jtf6.setText(MyDialogSetting3.slot_time1);
                        myDialogSetting3.setVisible(true);
                    }else if (row == 3 && column == 13) {           //点击（3，12）坐标的编辑按钮触发事件
                        MyDialogSetting4 myDialogSetting4 = null;
                        try {
                            myDialogSetting4 = new MyDialogSetting4(WindowTest.windowInitial);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        // 设置为模态
                        myDialogSetting4.setModal(true);
                        myDialogSetting4.setTitle("服务器4参数配置");
                        myDialogSetting4.setSize(400, 650);
                        myDialogSetting4.setResizable(false);
                        myDialogSetting4.setLocationRelativeTo(WindowTest.windowInitial);
                        LaunchParaPanel.jComboBox1.setSelectedItem(MyDialogSetting4.mod_type1);
                        LaunchParaPanel.jtf1.setText(MyDialogSetting4.m1_gen1);
                        LaunchParaPanel.jtf2.setText(MyDialogSetting4.m1_init1);
                        LaunchParaPanel.jtf3.setText(MyDialogSetting4.m2_gen1);
                        LaunchParaPanel.jtf4.setText(MyDialogSetting4.m2_init1);
                        LaunchParaPanel.jComboBox2.setSelectedItem(MyDialogSetting4.ecc_type1);
                        LaunchParaPanel.jtf5.setText(MyDialogSetting4.data_rate1);
                        LaunchParaPanel.jComboBox3.setSelectedItem(MyDialogSetting4.rf_fac1);
                        LaunchParaPanel.jtf6.setText(MyDialogSetting4.slot_time1);
                        myDialogSetting4.setVisible(true);
                    }
                }
            }
        });
    }

    private void initPanel()
    {
        this.panel = new JPanel();
        this.panel.setLayout(null);
    }

    /**
     * 这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格）
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        this.row = row;
        this.column = column;
        this.value = (String) value;
        this.button.setFont(new Font("Microsoft Yahei",Font.PLAIN,15));

        if (column == 4){
            this.button.setText("<html>选择<br>文件</html>");
        }else {
            this.button.setText((value == null) ? "" : value.toString());
        }
        return this.panel;
    }

    /**
     * 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。
     */
    @Override
    public Object getCellEditorValue()
    {
        return this.button.getText();
    }

}
