package ui.screens.subScreens;

import ui.Panel;

import javax.swing.*;

public class DescendingOrder extends SubUI {

    private JTable table;
    private JScrollPane panel;

    // use subframe
    public DescendingOrder(JFrame frame, Panel u, String title) {
        super(frame, u, title);
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
        panel = new JScrollPane(table);
        panel.setBounds(100,200,200,300);
        subFrame.add(panel);
    }

}
