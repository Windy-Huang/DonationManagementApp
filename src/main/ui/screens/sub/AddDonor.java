package ui.screens.sub;

import exceptions.DuplicateException;
import model.Donor;
import ui.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represent the addDonor screen
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
    private JLabel message;
    private JPanel panel;
    private JPanel buttonPanel;

    private String name;
    private String email;
    private String phone;

    // EFFECTS: create a subwindow to add a new donor
    public AddDonor(JFrame frame, Panel u, String title) {
        super(frame, u, title);
        panel = new JPanel();
        buttonPanel = new JPanel();
        init();
        setLayout();

        subFrame.getContentPane().setLayout(new FlowLayout());
        subFrame.getContentPane().add(buttonPanel);

        button.addActionListener(new GenerateAction());
        field1.addActionListener(new Action1());
        field2.addActionListener(new Action2());
        field3.addActionListener(new Action3());
    }

    // MODIFIES: this
    // EFFECTS: set the entry fields to empty status
    private void init() {
        label1 = new JLabel("Name:");
        warning1 = new JLabel();
        field1 = new JTextField();
        label2 = new JLabel("Email:");
        warning2 = new JLabel();
        field2 = new JTextField();
        label3 = new JLabel("Phone:");
        warning3 = new JLabel();
        field3 = new JTextField();

        button = new JButton("Add");
        message = new JLabel();
    }

    // MODIFIES: this
    // EFFECTS: Set layout manager for the panel
    private void setLayout() {
        panel.setLayout(new GridLayout(4, 3,10,10));
        panel.add(label1);
        panel.add(field1);
        panel.add(warning1);
        panel.add(label2);
        panel.add(field2);
        panel.add(warning2);
        panel.add(label3);
        panel.add(field3);
        panel.add(warning3);
        panel.add(new JLabel());
        panel.add(button);
        panel.add(new JLabel());

        buttonPanel.setLayout(new GridLayout(2,1,10,10));
        buttonPanel.add(panel);
        buttonPanel.add(message);
    }

    public void reset() {
        warning1.setText(null);
        field1.setText(null);
        warning2.setText(null);
        field2.setText(null);
        warning3.setText(null);
        field3.setText(null);
        message.setText(null);
        name = null;
        email = null;
        phone = null;
    }

    // represent a class that handles name input
    private class Action1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            name = field1.getText();
            warning1.setText("Name: " + name);
        }

    }

    // represent a class that handles email input
    private class Action2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            email = field2.getText();
            warning2.setText("Email: " + email);
        }

    }

    // represent a class that handles phone input
    private class Action3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            phone = field3.getText();
            warning3.setText("Phone: " + phone);
        }

    }

    // represent a class that handles new donor generation
    private class GenerateAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.isEnabled()) {
                Donor d = new Donor(name, email, phone);
                try {
                    user.addDonor(d);
                    message.setText("Donor successfully added. Please close the window.");
                } catch (DuplicateException ex) {
                    message.setText("Donor already exist. Please try again.");
                }
            }
        }

    }

}
