package com.hdh.util;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName GradientPanel
 * @Description TODO   渐变色面板工具类
 * @Author Kikityer
 * @Date 2019/8/18 12:13
 * @Version 1.0.0
 **/
public class GradientPanel extends JPanel {
    public GradientPanel(LayoutManager lm) {
        super(lm);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isOpaque()) {
            return;
        }

        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gradientPaint =new GradientPaint(width/2, height/2,new Color(24,116,205), width, height, new Color(16,78,139),false);


        g2.setPaint(gradientPaint);
        g2.fillRect(0, 0, width, height);

    }
}
