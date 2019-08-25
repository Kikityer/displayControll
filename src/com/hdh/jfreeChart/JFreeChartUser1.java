package com.hdh.jfreeChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName JFreeChartUser1
 * @Description TODO 服务器1 星图1显示
 * @Author Kikityer
 * @Date 2019/3/21 14:25
 * @Version 1.0.0
 **/
public class JFreeChartUser1 {
    /**
     * 用户1服务器的星图显示
     */

    public static XYSeriesCollection xySeriesCollection;
    public static XYSeries xyserie1;

    public static JPanel drawChart(){

        xySeriesCollection = new XYSeriesCollection();
        xyserie1 = new XYSeries("服务器1星座图");
        xyserie1.add(-16000,-16000);
        xyserie1.add(0,0);
        xyserie1.add(24000,24000);
        xySeriesCollection.addSeries(xyserie1);

        XYDataset dataset = xySeriesCollection;    //创建数据集对象
        JFreeChart chart = ChartFactory.createXYLineChart("",   //绘制折线图
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,false,false);

        chart.getTitle().setFont(new Font("Microsoft Yahei", Font.PLAIN, 12));     // 处理主标题的乱码
        chart.getLegend().setItemFont(new Font("Microsoft Yahei", Font.PLAIN, 12));// 处理子标题乱码
        XYPlot xyPlot = (XYPlot) chart.getPlot();       //获取图表区域对象
        xyPlot.setBackgroundPaint(new Color(24,116,205));
        xyPlot.setRangeGridlinePaint(Color.WHITE); //背景底部横虚线
        xyPlot.setDomainGridlinePaint(Color.WHITE);//背景底部竖虚线
        xyPlot.setOutlinePaint(Color.BLUE);//边界线
        xyPlot.setBackgroundAlpha(1f);// 设置背景透明度（0~1）
        xyPlot.setForegroundAlpha(1f);// 设置前景色透明度（0~1）
        XYLineAndShapeRenderer lasp = (XYLineAndShapeRenderer) xyPlot.getRenderer();
        lasp.setUseFillPaint(true);
        lasp.setSeriesStroke(0, new BasicStroke(3F));
        lasp.setSeriesPaint(0,Color.GREEN);//折线的颜色为白色

        /**
         * Y轴设置
         */
        NumberAxis numberAxis = (NumberAxis) xyPlot.getRangeAxis();  //获取y轴对象
        numberAxis.setTickLabelFont(new Font("Microsoft Yahei", Font.PLAIN, 12));// 处理Y轴上的乱码
        numberAxis.setLabelFont(new Font("Microsoft Yahei", Font.PLAIN, 12));    // 处理Y轴外的乱码
        numberAxis.setUpperMargin(0.1);
        numberAxis.setLowerMargin(0.1);
        numberAxis.setAutoRangeMinimumSize(2000);//最小跨度
        numberAxis.setLowerBound(-30000);//最小值显示
        numberAxis.setUpperBound(30000); //最大值显示
        numberAxis.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(8000);
        numberAxis.setTickUnit(unit);
        /**
         * X轴设置
         */
        NumberAxis numberAxis1 = (NumberAxis) xyPlot.getDomainAxis();
        numberAxis1.setTickLabelFont(new Font("Microsoft Yahei", Font.PLAIN, 12));// 处理X轴上的乱码
        numberAxis1.setLabelFont(new Font("Microsoft Yahei", Font.PLAIN, 12));    // 处理X轴外的乱码
        numberAxis1.setUpperMargin(0.1);
        numberAxis1.setLowerMargin(0.1);
        numberAxis1.setLowerBound(-30000);//最小值显示
        numberAxis1.setUpperBound(30000); //最大值显示
        numberAxis1.setAutoTickUnitSelection(false);
        NumberTickUnit unit1 = new NumberTickUnit(8000);
        numberAxis1.setTickUnit(unit1);

        ChartPanel localChartPanel = new ChartPanel(chart, false);
        localChartPanel.setOpaque(true);
        localChartPanel.setBackground(new Color(25,25,112));
//        localChartPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, new Color(	30,144,255)));
        return localChartPanel;
    }

}
