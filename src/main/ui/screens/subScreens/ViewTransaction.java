package ui.screens.subScreens;

import ui.Panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTransaction extends SubUI {

    private JLabel label;
    private JLabel warning;
    private JTextField field;
    private JButton button;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTable table;

    private String name;

    public ViewTransaction(JFrame frame, Panel u, String title) {
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
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            name = field.getText();
            // check if donor exist
            warning.setText("Name: " + name);
        }
    }

    private class GenerateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.isEnabled()) {
                // check if inside
                label1 = new JLabel("name");
                label1.setBounds(20,100,400,30);
                subFrame.add(label1);

                label2 = new JLabel("email");
                label2.setBounds(20,100,400,30);
                subFrame.add(label2);

                label3 = new JLabel("phone");
                label3.setBounds(20,100,400,30);
                subFrame.add(label3);

                label4 = new JLabel("amount");
                label4.setBounds(20,100,400,30);
                subFrame.add(label4);

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
                table = new JTable(data,column);
                table.setBounds(100,200,200,300);
                subFrame.add(table);
            }
        }
    }

}
