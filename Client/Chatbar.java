import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chatbar extends JTextField {

    private Chatbox chatbox;

    public Chatbar(Chatbox chatbox) {
        super(8);
        this.chatbox = chatbox;
        this.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent ev) {
                // System.out.println(ev.getKeyChar());
                if(ev.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("Enter key!");
                }
                // Chatbar textField = (Chatbar) ev.getSource();
                // String text = textField.getText();
                // textField.setText(text.toUpperCase());
            }

            public void keyTyped(KeyEvent ev) {}
            public void keyReleased(KeyEvent ev) {}

        });
    }
}