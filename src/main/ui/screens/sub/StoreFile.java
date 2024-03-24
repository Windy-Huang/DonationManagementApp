package ui.screens.sub;

import persistance.Writer;
import ui.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// represent the ui for storing data
public class StoreFile extends SubUI {

    private JLabel label;
    private JLabel warning;
    private JButton button;
    private JTextField field;
    private JPanel panel;
    private Writer writer;
    private String input;

    // EFFECTS: create the data storing interface
    public StoreFile(JFrame frame, Panel u, String title) {
        super(frame, u, title);
        label = new JLabel("Enter the file name, when finished press enter");
        warning = new JLabel();
        button = new JButton("Find");
        field = new JTextField();
        panel = new JPanel();
        writer = null;

        panel.setLayout(new GridLayout(6, 2,10,10));

        panel.add(label);
        panel.add(field);
        panel.add(warning);
        panel.add(button);

        subFrame.getContentPane().setLayout(new FlowLayout());
        subFrame.getContentPane().add(panel);

        button.addActionListener(new GenerateAction());
        field.addActionListener(new Action());
    }

    @Override
    public void reset() {
        field.setText(null);
        warning.setText(null);
        input = null;
    }

    // represent a class that handles file name search
    private class GenerateAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.isEnabled()) {
                String path = ("./data/" + input + ".json");
                try {
                    writer = new Writer(path);
                    writer.write(user);
                    warning.setText("Data successfully stored. Please close this window.");
                } catch (FileNotFoundException ex) {
                    warning.setText("The path entered does not exist, please try again.");
                }
            }
        }

    }

    // represent a class that takes user input
    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            input = field.getText();
        }

    }

}
