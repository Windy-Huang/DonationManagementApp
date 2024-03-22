package ui.screens.subScreens;

import ui.Panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private String name;
    private String month;
    private String day;
    private String year;
    private String type;
    private int amount;
    private Boolean amountBoolean = false;
    private Boolean donorBoolean = false;

    public AddTransaction(JFrame frame, Panel u, String title) {
        super(frame, u, title);
        label = new JLabel("Enter the name of donor to edit:");
        warning = new JLabel();
        field = new JTextField();
        label.setBounds(20,100,400,30);
        warning.setBounds(50,200,400,30);
        subFrame.add(label);
        subFrame.add(warning);
        field.setBounds(200, 350, 150,20);
        field.addActionListener(new Action());
        subFrame.add(field);

        label1 = new JLabel("month:");
        warning1 = new JLabel();
        field1 = new JTextField();
        label1.setBounds(20,100,400,30);
        warning1.setBounds(50,200,400,30);
        subFrame.add(label1);
        subFrame.add(warning1);
        field1.setBounds(200, 350, 150,20);
        field1.addActionListener(new Action1());
        subFrame.add(field1);

        label2 = new JLabel("day:");
        warning2 = new JLabel();
        field2 = new JTextField();
        label2.setBounds(20,100,400,30);
        warning2.setBounds(50,200,400,30);
        subFrame.add(label2);
        subFrame.add(warning2);
        field1.setBounds(200, 350, 150,20);
        field1.addActionListener(new Action2());
        subFrame.add(field2);

        label3 = new JLabel("year:");
        warning3 = new JLabel();
        field3 = new JTextField();
        label3.setBounds(20,100,400,30);
        warning3.setBounds(50,200,400,30);
        subFrame.add(label3);
        subFrame.add(warning3);
        field1.setBounds(200, 350, 150,20);
        field1.addActionListener(new Action3());
        subFrame.add(field3);

        label4 = new JLabel("type:");
        warning4 = new JLabel();
        field4 = new JTextField();
        label4.setBounds(20,100,400,30);
        warning4.setBounds(50,200,400,30);
        subFrame.add(label4);
        subFrame.add(warning4);
        field4.setBounds(200, 350, 150,20);
        field4.addActionListener(new Action4());
        subFrame.add(field4);

        label5 = new JLabel("amount:");
        warning5 = new JLabel();
        field5 = new JTextField();
        label5.setBounds(20,100,400,30);
        warning5.setBounds(50,200,400,30);
        subFrame.add(label5);
        subFrame.add(warning5);
        field5.setBounds(200, 350, 150,20);
        field5.addActionListener(new Action5());
        subFrame.add(field5);

        button = new JButton("Add");
        button.setBounds(200, 300, 150,20);
        button.addActionListener(new GenerateAction());
        subFrame.add(button);
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            name = field.getText();
            // check if donor exist
            donorBoolean = true;
            warning.setText("Name: " + name);
        }
    }

    private class Action1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            month = field1.getText();
            warning1.setText("month: " + month);
        }
    }

    private class Action2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            day = field2.getText();
            warning2.setText("day: " + day);
        }
    }

    private class Action3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            year = field3.getText();
            warning3.setText("year: " + year);
        }
    }

    private class Action4 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            type = field4.getText();
            warning4.setText("type: " + type);
        }
    }

    private class Action5 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = field5.getText();
            if (s.matches("[0-9]+")) {
                amount = Integer.valueOf(s);
                amountBoolean = true;
                warning5.setText("amount: " + amount);
            } else {
                warning5.setText("Enter a number");
            }
        }
    }

    private class GenerateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.isEnabled() && amountBoolean && donorBoolean) {
                // find data
            }
        }
    }

}
