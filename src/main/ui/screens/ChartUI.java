package ui.screens;

import javax.swing.*;
import java.awt.*;

public class ChartUI extends Canvas {

    private JFrame frame;
    private int x0 = 100;
    private int y0 = 100;
    private int y1 = 600;
    private int x1 = 600;
    private int blank = 10;
    private int width = 20;
    private int v1 = 40;
    private int v2 = 70;
    private int v3 = 10;
    private int v4 = 90;
    private int v5 = 50;
    private int v6 = 20;


    public ChartUI(JFrame frame) {
        this.frame = frame;
        setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(x0,y0,x0,y1);
        g.drawLine(x0,y1,x1,y1);
        setBackground(Color.WHITE);
        setForeground(Color.BLUE);
        g.fillRect(x0 + blank, y1 - v1,width, v1);
        g.fillRect(x0 + (2 * blank) + (1 * width), y1 - v2, width, v2);
        g.fillRect(x0 + (3 * blank) + (2 * width), y1 - v3, width, v3);
        g.fillRect(x0 + (4 * blank) + (3 * width), y1 - v4, width, v4);
        g.fillRect(x0 + (5 * blank) + (4 * width), y1 - v5, width, v5);
        g.fillRect(x0 + (6 * blank) + (5 * width), y1 - v6, width, v6);
    }

}
