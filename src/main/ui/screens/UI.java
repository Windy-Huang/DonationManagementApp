package ui.screens;

import javax.swing.*;
import java.awt.*;

// represent the main frame for login and home page
public abstract class UI {

    protected JFrame frame;

    // EFFECTS: defines characteristics of frame
    protected UI(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,600));
        frame.setLayout(null);
        frame.setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: make the screen visible
    protected void visible() {
        frame.pack();
        frame.setVisible(true);
    }

}
