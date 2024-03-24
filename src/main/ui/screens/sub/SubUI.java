package ui.screens.sub;

import ui.Panel;
import javax.swing.*;
import java.awt.*;

// represent the subFrame for home page option
public abstract class SubUI {

    protected JFrame mainFrame;
    protected JDialog subFrame;
    protected Panel user;

    // EFFECTS: defines characteritics of frame
    protected SubUI(JFrame frame, Panel user, String title) {
        mainFrame = frame;
        this.user = user;
        subFrame = new JDialog(mainFrame,title,true);
        subFrame.setPreferredSize(new Dimension(700,500));
        subFrame.setLayout(null);
        subFrame.setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: make the screen visible
    public void visible() {
        subFrame.pack();
        subFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: reset the window to empty status
    public abstract void reset();

}
