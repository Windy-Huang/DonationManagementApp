package ui.screens.sub;

import ui.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represent the view with Threshold screen
public class Threshold extends SubUI {

    private JLabel label;
    private JLabel warning;
    private JTextField field;
    private JButton button;
    private JTable table;
    private int min;

    private JScrollPane scroll;
    private JPanel panel;
    private JPanel instruction;

    // EFFECTS: create a subwindow to view details of donors above a certain donatino
    public Threshold(JFrame frame, Panel u, String title) {
        super(frame, u, title);
        panel = new JPanel();
        instruction = new JPanel();
        scroll = new JScrollPane();

        label = new JLabel("Enter the minimum donation to surpass:");
        warning = new JLabel();
        field = new JTextField();
        button = new JButton("Generate");
        min = 0;

        setLayout();
        subFrame.getContentPane().setLayout(new FlowLayout());
        subFrame.getContentPane().add(panel);

        button.addActionListener(new GenerateAction());
        field.addActionListener(new Action());
    }

    // MODIFIES: this
    // EFFECTS: Set layout manager for the panel
    private void setLayout() {
        instruction.setLayout(new GridLayout(4, 1,10,10));
        instruction.add(label);
        instruction.add(field);
        instruction.add(warning);
        instruction.add(button);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(instruction);
        panel.add(scroll);
    }

    @Override
    public void reset() {
        warning.setText(null);
        field.setText(null);
        min = 0;
        scroll.setViewportView(null);
    }

    // represent a class that handles filter donor statistics
    private class GenerateAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.isEnabled()) {
                String [][] data = user.donorOverValue(min);
                String [] column = {"Name","Donation Made"};
                table = new JTable(data,column);
                scroll.setViewportView(table);
                scroll.setPreferredSize(new Dimension(200, 300));
                subFrame.revalidate();
            }
        }

    }

    // represent a class that handles number input
    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = field.getText();
            if (!str.matches("[0-9]+")) {
                warning.setText("Please enter a number");
            } else {
                min = Integer.valueOf(str);
                warning.setText("Value: " + min);
            }
        }

    }

}
