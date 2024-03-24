package ui.screens.sub;

import model.Date;
import model.Donor;
import model.Transaction;
import ui.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represent the addTransaction screen
public class AddTransaction extends SubUI {

    private JLabel label;
    private JLabel warning;
    private JTextField field;

    private JLabel label1;
    private JLabel warning1;
    private JTextField field1;

    private JLabel label2;
    private JLabel warning2;
    private JTextField field2;

    private JLabel label3;
    private JLabel warning3;
    private JTextField field3;

    private JLabel label4;
    private JLabel warning4;
    private JTextField field4;

    private JLabel label5;
    private JLabel warning5;
    private JTextField field5;

    private JButton button;
    private JLabel message;
    private JPanel panel;
    private JPanel buttonPanel;

    private String name;
    private int month;
    private int day;
    private int year;
    private String type;
    private int amount;

    // EFFECTS: create a subwindow to add a new transaction
    public AddTransaction(JFrame frame, Panel u, String title) {
        super(frame, u, title);
        panel = new JPanel();
        buttonPanel = new JPanel();
        init();
        setLayout();
        buttonPanel.setLayout(new GridLayout(2,1,10,10));
        buttonPanel.add(panel);
        buttonPanel.add(message);

        subFrame.getContentPane().setLayout(new FlowLayout());
        subFrame.getContentPane().add(buttonPanel);

        button.addActionListener(new GenerateAction());
        field.addActionListener(new Action());
        field1.addActionListener(new Action1());
        field2.addActionListener(new Action2());
        field3.addActionListener(new Action3());
        field4.addActionListener(new Action4());
        field5.addActionListener(new Action5());
    }

    // MODIFIES: this
    // EFFECTS: set the entry fields to empty status
    private void init() {
        label = new JLabel("Enter the name of donor to edit:");
        warning = new JLabel();
        field = new JTextField();
        label1 = new JLabel("month:");
        warning1 = new JLabel();
        field1 = new JTextField();
        label2 = new JLabel("day:");
        warning2 = new JLabel();
        field2 = new JTextField();
        label3 = new JLabel("year:");
        warning3 = new JLabel();
        field3 = new JTextField();
        label4 = new JLabel("type:");
        warning4 = new JLabel();
        field4 = new JTextField();
        label5 = new JLabel("amount:");
        warning5 = new JLabel();
        field5 = new JTextField();

        button = new JButton("Add");
        message = new JLabel();
    }

    // MODIFIES: this
    // EFFECTS: Set layout manager for the panel
    private void setLayout() {
        panel.setLayout(new GridLayout(7, 3,10,10));
        panel.add(label);
        panel.add(field);
        panel.add(warning);
        panel.add(label1);
        panel.add(field1);
        panel.add(warning1);
        panel.add(label2);
        panel.add(field2);
        panel.add(warning2);
        panel.add(label3);
        panel.add(field3);
        panel.add(warning3);
        panel.add(label4);
        panel.add(field4);
        panel.add(warning4);
        panel.add(label5);
        panel.add(field5);
        panel.add(warning5);
        panel.add(new JLabel());
        panel.add(button);
        panel.add(new JLabel());
    }

    @Override
    public void reset() {
        warning.setText(null);
        field.setText(null);
        warning1.setText(null);
        field1.setText(null);
        warning2.setText(null);
        field2.setText(null);
        warning3.setText(null);
        field3.setText(null);
        warning4.setText(null);
        field4.setText(null);
        warning5.setText(null);
        field5.setText(null);
        message.setText(null);
        name = null;
        month = 0;
        day = 0;
        year = 0;
        type = null;
        amount = 0;
    }

    // represent a class that handles name input
    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            name = field.getText();
            warning.setText("Name: " + name);
        }

    }

    // represent a class that handles month input
    private class Action1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = field1.getText();
            if (s.matches("[1-9]|(1[0-2])")) {
                month = Integer.valueOf(s);
                warning1.setText("month: " + month);
            } else {
                warning1.setText("Please enter a valid month");
            }
        }

    }

    // represent a class that handles day input
    private class Action2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = field2.getText();
            if (s.matches("[1-9]|([1-2][0-9])|(3[0-1])")) {
                day = Integer.valueOf(s);
                warning2.setText("day: " + day);
            } else {
                warning2.setText("Please enter a valid day");
            }
        }

    }

    // represent a class that handles year input
    private class Action3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = field3.getText();
            if (s.matches("[0-9]+")) {
                year = Integer.valueOf(s);
                warning3.setText("year: " + year);
            } else {
                warning3.setText("Please enter a number");
            }
        }

    }

    // represent a class that handles type input
    private class Action4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            type = field4.getText();
            warning4.setText("type: " + type);
        }

    }

    // represent a class that handles amount input
    private class Action5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = field5.getText();
            if (s.matches("[0-9]+")) {
                amount = Integer.valueOf(s);
                warning5.setText("amount: " + amount);
            } else {
                warning5.setText("Please enter a number");
            }
        }

    }

    // represent a class that handles new donor generation
    private class GenerateAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object index = user.findDonor(new Donor(name, null, null));
            if (button.isEnabled()) {
                if (index == null) {
                    message.setText("Donor does not exist. Please try again");
                } else if (amount == 0 || month == 0 || day == 0 || year == 0) {
                    message.setText("Please check the numerical inputs");
                } else {
                    Transaction t = new Transaction(new Date(month, day, year), type, amount);
                    user.getDonors().get((Integer) index).addTransaction(t);
                    user.updateDonation();
                    message.setText("Transaction successfully added. Please close the window");
                }
            }
        }

    }

}
