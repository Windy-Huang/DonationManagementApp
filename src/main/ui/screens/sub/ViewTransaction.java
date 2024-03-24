package ui.screens.sub;

import model.Donor;
import ui.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represent the viewTransaction screen
public class ViewTransaction extends SubUI {

    private JLabel label;
    private JLabel warning;
    private JTextField field;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTable table;

    private JButton button;
    private JLabel message;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JScrollPane scroll;
    private JPanel buttonPanel;

    private String name;

    // EFFECTS: create a subwindow to view details about a donor
    public ViewTransaction(JFrame frame, Panel u, String title) {
        super(frame, u, title);
        panelLeft = new JPanel();
        panelRight = new JPanel();
        buttonPanel = new JPanel();
        scroll = new JScrollPane();

        label = new JLabel("Enter the name of donor to view:");
        warning = new JLabel();
        field = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        button = new JButton("View");
        message = new JLabel();

        setLayout();
        subFrame.getContentPane().setLayout(new FlowLayout());
        subFrame.getContentPane().add(buttonPanel);

        field.addActionListener(new Action());
        button.addActionListener(new GenerateAction());
    }

    // MODIFIES: this
    // EFFECTS: Set layout manager for the panel
    private void setLayout() {
        panelLeft.setLayout(new GridLayout(4, 1,10,10));
        panelLeft.add(label);
        panelLeft.add(field);
        panelLeft.add(button);
        panelLeft.add(message);

        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        panelRight.add(label1);
        panelRight.add(label2);
        panelRight.add(label3);
        panelRight.add(label4);
        panelRight.add(scroll);

        buttonPanel.setLayout(new GridLayout(1,2,10,10));
        buttonPanel.add(panelLeft);
        buttonPanel.add(panelRight);
    }

    @Override
    public void reset() {
        warning.setText(null);
        field.setText(null);
        label1.setText(null);
        label2.setText(null);
        label3.setText(null);
        label4.setText(null);
        name = null;
        scroll.setViewportView(null);
    }

    // represent a class that handles name input
    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            name = field.getText();
            warning.setText("Name: " + name);
        }

    }

    // represent a class that handles view donor statistics
    private class GenerateAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object index = user.findDonor(new Donor(name, null, null));
            if (button.isEnabled()) {
                if (index == null) {
                    message.setText("Donor does not exist. Please try again");
                } else {
                    Donor d = user.getDonors().get((Integer) index);
                    message.setText("Successful");
                    label1.setText("Donor Name: " + d.getName());
                    label2.setText("Donor Email: " + d.getEmail());
                    label3.setText("Donor Phone: " + d.getPhone());
                    label4.setText("Donation: " + d.getDonation());

                    String[][] data = d.transactionArray();
                    String [] column = {"Date","Amount"};
                    table = new JTable(data,column);
                    scroll.setViewportView(table);
                    scroll.setPreferredSize(new Dimension(200, 300));
                    subFrame.revalidate();
                }
            }
        }

    }

}
