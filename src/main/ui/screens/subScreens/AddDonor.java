package ui.screens.subScreens;

import ui.Panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDonor extends SubUI {

    private JLabel label1;
    private JLabel warning1;
    private JTextField field1;

    private JLabel label2;
    private JLabel warning2;
    private JTextField field2;

    private JLabel label3;
    private JLabel warning3;
    private JTextField field3;

    private JButton button;

    private String name;
    private String email;
    private String phone;

    public AddDonor(JFrame frame, Panel u, String title) {
        super(frame, u, title);
        label1 = new JLabel("Name:");
        warning1 = new JLabel();
        field1 = new JTextField();
        label1.setBounds(20,100,400,30);
        warning1.setBounds(50,200,400,30);
        subFrame.add(label1);
        subFrame.add(warning1);
        field1.setBounds(200, 350, 150,20);
        field1.addActionListener(new Action1());
        subFrame.add(field1);

        label2 = new JLabel("Email:");
        warning2 = new JLabel();
        field2 = new JTextField();
        label2.setBounds(20,100,400,30);
        warning2.setBounds(50,200,400,30);
        subFrame.add(label2);
        subFrame.add(warning2);
        field1.setBounds(200, 350, 150,20);
        field1.addActionListener(new Action2());
        subFrame.add(field2);

        label3 = new JLabel("Phone:");
        warning3 = new JLabel();
        field3 = new JTextField();
        label3.setBounds(20,100,400,30);
        warning3.setBounds(50,200,400,30);
        subFrame.add(label3);
        subFrame.add(warning3);
        field1.setBounds(200, 350, 150,20);
        field1.addActionListener(new Action3());
        subFrame.add(field3);

        button = new JButton("Add");
        button.setBounds(200, 300, 150,20);
        button.addActionListener(new GenerateAction());
        subFrame.add(button);
    }

    private class Action1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            name = field1.getText();
            warning1.setText("Name: " + name);
        }
    }

    private class Action2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            email = field2.getText();
            warning2.setText("Email: " + email);
        }
    }

    private class Action3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            phone = field3.getText();
            warning3.setText("Phone: " + phone);
        }
    }

    private class GenerateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.isEnabled()) {
                // find data
            }
        }
    }

}
