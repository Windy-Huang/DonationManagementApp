package ui.screens;

import java.awt.*;

// represent a graph that showcase how much the organization received per month
public class ChartUI extends Canvas {

    private final int x0 = 100;
    private final int y0 = 50;
    private final int y1 = 350;
    private final int x1 = 680;
    private final int blank = 20;
    private final int width = 25;
    private final int margin = 25;
    private final String[] label = new String[]{"Jan", "Feb", "Mar",
            "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private int[] value;
    private double factor;

    // EFFECTS: instantiate the graph and create the rescaling variable
    public ChartUI(int goal) {
        value = new int[12];
        for (int i = 0; i < 12; i++) {
            value[i] = 0;
        }
        this.factor = (double) (y1 - y0) / (double) goal;
    }

    // EFFECTS: Visualize the data and render it on screen
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(x0,y0,x0,y1);
        g.drawLine(x0,y1,x1,y1);
        setBackground(Color.WHITE);
        setForeground(Color.BLUE);
        for (int i = 0; i < value.length; i++) {
            g.fillRect(x0 + ((i + 1) * blank) + (i * width), y1 - value[i], width, value[i]);
            g.drawString(label[i], x0 + ((i + 1) * blank) + (i * width), y1 + margin);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(-Math.PI / 2); // Rotate by -90 degrees
        g2d.drawString("Donation received", (-y1 / 2) - 75, x0 - margin);
    }

    // MODIFIES: this
    // EFFECTS: updates the data to be shown on screen
    public void update(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            value[i] = (int) (factor * arr[i]);
        }
    }

}
