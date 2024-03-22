package ui.screens.subScreens;

import persistance.Reader;
import ui.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// represent the ui for loading data
public class LoadFile extends SubUI {

    private JLabel label;
    private JLabel warning;
    private JButton button;
    private JTextField field;
    private JPanel panel;
    private Reader reader;
    private String input;

    // EFFECTS: create the data reading interface
    public LoadFile(JFrame frame, Panel u, String title) {
        super(frame, u, title);
        label = new JLabel("Enter the file name to load, when finished press enter");
        warning = new JLabel();
        button = new JButton("Find");
        field = new JTextField();
        panel = new JPanel();
        reader = null;

        panel.setLayout(new GridLayout(6, 2,10,10));

        // Add components to panel with specified regions
        panel.add(label);
        panel.add(field);
        panel.add(warning);
        panel.add(button);

        // Set size of the panel explicitly
        subFrame.getContentPane().setLayout(new FlowLayout());
        subFrame.getContentPane().add(panel);

        button.addActionListener(new GenerateAction());
        field.addActionListener(new Action());
    }

    // represent a class that handles file name search
    private class GenerateAction implements ActionListener {

        // MODIFIES: this
        // EFFECTS: loads the data into application, else notify user to try again
        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.isEnabled()) {
                String path = ("./data/" + input + ".json");
                try {
                    reader = new Reader(path);
                    user = reader.build();
                    warning.setText("Data successfully loaded. Please close the window.");
                } catch (IOException ex) {
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
