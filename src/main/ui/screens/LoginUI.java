package ui.screens;

import exceptions.EmptyException;
import model.Account;
import ui.Panel;

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
    private Panel user;

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
        user = new Panel();

        setLayout();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(messagePanel);

        button.addActionListener(new LoginAction());

        super.visible();
    }

    // MODIFIES: this
    // EFFECTS: Set layout manager for the panel
    private void setLayout() {
        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(label);
        panel.add(value);
        panel.add(new JLabel());
        panel.add(button);
        panel.add(new JLabel());
        panel.add(new JLabel());

        messagePanel.setLayout(new GridLayout(3,1));
        messagePanel.add(panel);
        messagePanel.add(message);
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
                    new HomeScreenUI(user);
                } else {
                    message.setText("Wrong password, please try again.");
                }
            } catch (EmptyException ex) {
                message.setText("Empty password, please try again.");
            }
        }

    }

}
