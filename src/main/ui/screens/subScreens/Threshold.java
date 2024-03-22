package ui.screens.subScreens;

import ui.Panel;
import ui.screens.HomeScreenUI;
import ui.screens.LoginUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Threshold extends SubUI {

    private JLabel label;
    private JLabel warning;
    private JButton button;
    private JTextField field;
    private int min;

    public Threshold(JFrame frame, Panel u, String title) {
        super(frame, u, title);
        label = new JLabel("Enter a minimum donated value:");
        warning = new JLabel();
        button = new JButton("Generate");
        field = new JTextField();
        min = 0;

        label.setBounds(20,100,400,30);
        warning.setBounds(50,200,400,30);
        subFrame.add(label);
        subFrame.add(warning);

        button.setBounds(200, 300, 150,20);
        button.addActionListener(new GenerateAction());
        subFrame.add(button);

        field.setBounds(200, 350, 150,20);
        field.addActionListener(new Action());
        subFrame.add(field);
    }

    private class GenerateAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.isEnabled()) {
                String [][] data = { {"Amit","670000"},
                        {"Jai","780000"},
                        {"Sachin","700000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"},
                        {"Jai","780000"}};
                String [] column = {"NAME","DONATION"};
                JTable table = new JTable(data,column);
                table.setBounds(100,200,200,300);
                JScrollPane panel = new JScrollPane(table);
                panel.setBounds(100,200,200,300);
                subFrame.add(panel);
            }
        }
    }

    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = field.getText();
            if (!str.matches("[0-9]+")) {
                warning.setText("Please enter integer");
            } else {
                min = Integer.valueOf(str);
            }
            System.out.println(str);
        }
    }

}
