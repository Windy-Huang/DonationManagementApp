package ui.screens;

import model.EventLog;
import ui.screens.sub.*;
import ui.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// represent the home screen of the application
public class HomeScreenUI extends UI {

    private EventLog log;
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
    private JPanel chartPanel;
    private JPanel buttonPanel;
    private JPanel messagePanel;

    private JLabel label1;
    private JLabel label2;
    private JProgressBar progress;
    private int donation;
    private int goal;

    private ChartUI graph;

    // EFFECTS: create a main window to access all subwindows of the application
    public HomeScreenUI(Panel user) {
        super("Home", user);
        log = EventLog.getInstance();
        bar = new JMenuBar();
        setMenu();
        root = frame.getRootPane();
        root.setJMenuBar(bar);

        label1 = new JLabel("       RMCS has collected 0 dollars thanks to the help of 0 donors");
        label2 = new JLabel("       Our progress in reaching our donation goal is:");
        donation = 0;
        goal = 2000;
        progress = new JProgressBar(donation, goal);
        progress.setBounds(40,40,160,30);
        progress.setStringPainted(true);
        graph = new ChartUI(goal);

        button = new JButton("Log Off");
        button.setBounds(200, 300, 150,20);

        setLayout();
        addListener();
        super.visible();
    }

    // MODIFIES: this
    // EFFECTS: handles adding action listener to variety of interactive features
    private void addListener() {
        i1.addActionListener(new ItemListener(new LoadFile(frame, user, "load"), i1));
        i2.addActionListener(new ItemListener(new StoreFile(frame, user, "store"), i2));
        i3.addActionListener(new ItemListener(new DescendingOrder(frame, user, "descending order"), i3));
        i4.addActionListener(new ItemListener(new Threshold(frame, user, "threshold"), i4));
        i5.addActionListener(new ItemListener(new AddDonor(frame, user, "add donor"), i5));
        i6.addActionListener(new ItemListener(new AddTransaction(frame, user, "add transaction"), i6));
        i7.addActionListener(new ItemListener(new ViewTransaction(frame, user, "view transaction"), i7));
        button.addActionListener(new LogOffAction());
        frame.addWindowListener(new QuitAction());
    }

    // MODIFIES: this
    // EFFECTS: handles the creation of navigation bar
    private void setMenu() {
        m1 = new JMenu("Manage Files");
        bar.add(m1);
        i1 = new JMenuItem("Load File");
        m1.add(i1);
        i2 = new JMenuItem("Store File");
        m1.add(i2);

        m2 = new JMenu("View all Donor");
        bar.add(m2);
        i3 = new JMenuItem("View in Descending Order");
        m2.add(i3);
        i4 = new JMenuItem("View with Threshold");
        m2.add(i4);

        m3 = new JMenu("Add a Donor");
        bar.add(m3);
        i5 = new JMenuItem("Add Donor");
        m3.add(i5);

        m4 = new JMenu("Manage a Donor");
        bar.add(m4);
        i6 = new JMenuItem("Add Transaction");
        m4.add(i6);
        i7 = new JMenuItem("View Transaction");
        m4.add(i7);
    }

    // MODIFIES: this
    // EFFECTS: Set layout manager for the panel
    private void setLayout() {
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(button);
        chartPanel = new JPanel(new BorderLayout());
        chartPanel.add(graph, BorderLayout.CENTER);
        messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(3,1,10,10));
        messagePanel.add(label1);
        messagePanel.add(label2);
        messagePanel.add(progress);

        frame.setJMenuBar(bar);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(messagePanel, BorderLayout.NORTH);
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: updates the visualization of data on screen
    private void update() {
        progress.setValue(user.getTotalDonation());
        label1.setText("       RMCS has collected " + user.getTotalDonation()
                + " donations thanks to the help of " + user.getDonors().size() + " donors");
        int[] arr = user.transactionData();
        chartPanel.remove(graph);
        graph.update(arr);
        chartPanel.add(graph, BorderLayout.CENTER);
        frame.revalidate();
    }

    // represent a class that handles opening subwindows
    private class ItemListener implements ActionListener {

        private SubUI screen;
        private JMenuItem item;

        // EFFECTS: instantiate the subwindow
        public ItemListener(SubUI screen, JMenuItem i) {
            this.screen = screen;
            item = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (item.isEnabled()) {
                screen.reset();
                screen.visible();
                update();
            }
        }

    }

    // represent a class that handles logoff from application
    private class LogOffAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.isEnabled()) {
                log.printLog();
                System.exit(0);
            }
        }

    }

    // represent a class that handles user unexpectedly close the home screen
    private class QuitAction implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            log.printLog();
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }

    }

}
