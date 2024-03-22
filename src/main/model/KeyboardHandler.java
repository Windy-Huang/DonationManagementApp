package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// represent a class that handles all user input with the program
public class KeyboardHandler implements KeyListener {

    private String input;

    // EFFECTS: builds an object with empty stream and assigned to a ScreenUI
    public KeyboardHandler() {
        input = "";
    }

    // MODIFIES: this
    // EFFECTS: adds the character to the input stream
    @Override
    public void keyTyped(KeyEvent e) {
        input += e.getKeyChar();
        System.out.println("typed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    // MODIFIES: this, obj
    // EFFECTS: returns and clear the input stream when enter is pressed
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//            object.setInput(input);
            System.out.println(input);
            input = "";
        }
    }

}
