package ui.screens;

import ui.Panel;
import ui.screens.subScreens.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreenUI extends UI {

    private Panel user;

    private JRootPane root;

    private JMenuBar bar;
    private JMenu m1;
    private JMenu m2;
    private JMenu m3;
    private JMenu m4;
    private JMenuItem i1;
    private JMenuItem i2;
    private JMenuItem i3;
    private JMenuItem i4;
    private JMenuItem i5;
    private JMenuItem i6;
    private JMenuItem i7;

    private JButton button;

    private JProgressBar progress;
    private int donation = 0;
    private int goal = 2000;

    private ChartUI graph;

    public HomeScreenUI() {
        super("Home");
        user = new Panel();
        bar = new JMenuBar();

        m1 = new JMenu("Manage Files");
        bar.add(m1);
        i1 = new JMenuItem("Load File");
        i1.addActionListener(new ItemListener(new LoadFile(frame, user, "load"), i1));
        m1.add(i1);
        i2 = new JMenuItem("Store File");
        i2.addActionListener(new ItemListener(new StoreFile(frame, user, "store"), i2));
        m1.add(i2);

        m2 = new JMenu("View all Donor");
        bar.add(m2);
        i3 = new JMenuItem("View in Descending Order");
        i3.addActionListener(new ItemListener(new DescendingOrder(frame, user, "descending order"), i3));
        m2.add(i3);
        i4 = new JMenuItem("View with Threshold");
        i4.addActionListener(new ItemListener(new Threshold(frame, user, "threshold"), i4));
        m2.add(i4);

        m3 = new JMenu("Add a Donor");
        bar.add(m3);
        i5 = new JMenuItem("Add Donor");
        i5.addActionListener(new ItemListener(new AddDonor(frame, user, "add donor"), i5));
        m3.add(i5);

        m4 = new JMenu("Manage a Donor");
        bar.add(m4);
        i6 = new JMenuItem("Add Transaction");
        i6.addActionListener(new ItemListener(new AddTransaction(frame, user, "add transaction"), i6));
        m4.add(i6);
        i7 = new JMenuItem("View Transaction");
        i7.addActionListener(new ItemListener(new ViewTransaction(frame, user, "view transaction"), i7));
        m4.add(i7);

        button = new JButton("Log Off");
        button.setBounds(200, 300, 150,20);
        button.addActionListener(new LogOffAction());
        frame.add(button);

        root = frame.getRootPane();
        root.setJMenuBar(bar);

        progress = new JProgressBar(donation, goal);
        progress.setBounds(40,40,160,30);
        progress.setValue(1000);
        progress.setStringPainted(true);
        frame.add(progress);

        graph = new ChartUI(frame);

        super.visible();
    }

    private class ItemListener implements ActionListener {

        private SubUI screen;
        private JMenuItem item;

        public ItemListener(SubUI screen, JMenuItem i) {
            this.screen = screen;
            item = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (item.isEnabled()) {
                screen.visible();
            }
        }
    }

    private class LogOffAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.isEnabled()) {
                System.exit(0);
            }
        }
    }



}
