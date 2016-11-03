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
                // Fire this action only if the user pressed enter
                if(ev.getKeyCode() != KeyEvent.VK_ENTER) return;

                String content;
                Chatbar textField = (Chatbar) ev.getSource();

                content = textField.getText();

                textField.setText("");
                chatbox.add(content);
            }

            public void keyTyped(KeyEvent ev) {}
            public void keyReleased(KeyEvent ev) {}

        });
    }
}