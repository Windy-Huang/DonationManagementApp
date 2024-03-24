package ui.screens.sub;

import ui.Panel;

import javax.swing.*;
import java.awt.*;

// represent the decreasingOrder screen
public class DescendingOrder extends SubUI {

    private JLabel label;
    private JTable table;
    private JScrollPane scroll;
    private JPanel panel;

    // EFFECTS: create a subwindow to view donor in decreasing donation order
    public DescendingOrder(JFrame frame, Panel u, String title) {
        super(frame, u, title);
        table = new JTable();
        panel = new JPanel();
        scroll = new JScrollPane(table);
        label = new JLabel("The following donors are displayed in decreasing donation");

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);
        panel.add(scroll);

        subFrame.getContentPane().setLayout(new FlowLayout());
        subFrame.getContentPane().add(panel);
    }

    @Override
    public void reset() {
        String[][] data = user.donorArray();
        String [] column = {"Name","Donation Made"};
        table = new JTable(data,column);
        scroll.setViewportView(table);
        scroll.setPreferredSize(new Dimension(200, 300));
        subFrame.revalidate();
    }

}
