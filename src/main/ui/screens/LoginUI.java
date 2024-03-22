package ui.screens;

import exceptions.EmptyException;
import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represent the login screen
public class LoginUI extends UI {

    private JLabel label;
    private JLabel message;
    private JButton button;
    private JPasswordField value;
    private JPanel panel;
    private JPanel messagePanel;
    private Account account;

    // EFFECTS: create a login window
    public LoginUI() {
        super("Login");
        label = new JLabel("Password:");
        message = new JLabel();
        button = new JButton("login");
        value = new JPasswordField();
        panel = new JPanel();
        messagePanel = new JPanel();
        account = new Account("RMCS", "123");

        // Set layout manager for the panel
        panel.setLayout(new GridLayout(5, 2));

        // Add components to panel with specified regions
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(label);
        panel.add(value);
        panel.add(new JLabel());
        panel.add(button);
        panel.add(new JLabel());
        panel.add(new JLabel());
        messagePanel.add(message);

        // Set size of the panel explicitly
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(panel);
        frame.getContentPane().add(messagePanel);

        // Add action listeners
        button.addActionListener(new LoginAction());

        super.visible();
    }

    // represent the class that handle user inputted password
    private class LoginAction implements ActionListener {

        // MODIFIES: this
        // EFFECTS: moves to the home page if user enters correct password
        //          else prompt user to try again
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                char[] arr = value.getPassword();
                String pass = new String(arr);
                if (account.login(pass)) {
                    frame.setVisible(false);
                    HomeScreenUI home = new HomeScreenUI();
                } else {
                    message.setText("Wrong password, please try again.");
                }
            } catch (EmptyException ex) {
                message.setText("Empty password, please try again.");
            }
        }

    }

}
