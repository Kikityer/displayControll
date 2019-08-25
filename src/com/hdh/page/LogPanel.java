package com.hdh.page;

import com.hdh.util.ColumnColor;
import com.hdh.util.LimitedQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * @ClassName LogPanel
 * @Description TODO   日志显示区
 * @Author Kikityer
 * @Date 2019/3/21 14:44
 * @Version 1.0.0
 **/
public class LogPanel extends JPanel{
    /**
     * 日志显示区
     * 1：显示的格式（序号 时间 类型 信息描述）
     * 2：显示的条数（50条 有序号）
     * 3：每生成一条日志持久化到硬盘中
     * 4：不同的日志类型信息显示不同的颜色ps：暂定（成功发送和接收服务器的信息：绿色。点击等操作事件：白色。错误信息日志：红色）
     */
    public static JTable table;
    public static LimitedQueue<String[]> limitedQueue = new LimitedQueue<>(50);
    JScrollPane jscrollpane = new JScrollPane();

    LogPanel(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WindowInitial.dataPanelWidth,WindowInitial.screenHeight-WindowInitial.dataPanelHeight));

        String headName[] = {"序号","时间","类型","信息描述"}; //列名
        Object[][] rowData = new Object[50][4];//行数，列数
        table = new JTable(rowData,headName);
        table.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
        table.getTableHeader().setPreferredSize(new Dimension(WindowInitial.dataPanelWidth, (int) (WindowInitial.screenHeight*0.3*0.15)));//设置表单的表头高度
        table.getTableHeader().setFont(new Font("Microsoft Yahei", Font.BOLD, 16));
        table.setRowSelectionAllowed(false);  //关闭选择一行
        table.setShowGrid(true);  //是否显示网格
        table.setForeground(Color.BLACK);
        table.setRowHeight((int) (WindowInitial.screenHeight*0.3*0.12));
        // 设置列标题不能移动
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ColumnColor.setColumnColor(table);
        jscrollpane.setViewportView(table);
        jscrollpane.setAutoscrolls(true);

        TableColumnModel cm = table.getColumnModel();
        TableColumn column = cm.getColumn(0); //获取jtable中第一列对象
        column.setPreferredWidth((int) (WindowInitial.dataPanelWidth*0.1));
        column.setMaxWidth((int) (WindowInitial.dataPanelWidth*0.1) + 10);
        column.setMinWidth((int) (WindowInitial.dataPanelWidth*0.1) - 10);
        TableColumn column1 = cm.getColumn(1); //获取jtable中第二列对象
        column1.setPreferredWidth((int) (WindowInitial.dataPanelWidth*0.2));
        column1.setMaxWidth((int) (WindowInitial.dataPanelWidth*0.2) + 10);
        column1.setMinWidth((int) (WindowInitial.dataPanelWidth*0.2) - 10);
        TableColumn column2 = cm.getColumn(2); //获取jtable中第三列对象
        column2.setPreferredWidth((int) (WindowInitial.dataPanelWidth*0.1));
        column2.setMaxWidth((int) (WindowInitial.dataPanelWidth*0.1) + 10);
        column2.setMinWidth((int) (WindowInitial.dataPanelWidth*0.1) - 10);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);

        this.add(jscrollpane);

    }
}
