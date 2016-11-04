import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Chatbar extends JTextField {

    private Chatbox chatbox;
    private DataOutputStream out;

    public Chatbar(Chatbox chatbox, DataOutputStream out) {
        super(8);
        this.chatbox = chatbox;
        this.out = out;

        this.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent ev) {
                // Fire this action only if the user pressed enter
                if(ev.getKeyCode() != KeyEvent.VK_ENTER) return;

                String content;
                Chatbar textField = (Chatbar) ev.getSource();

                content = textField.getText();

                textField.setText("");
                chatbox.add(content);

                try {
                    out.writeUTF(content);
                    System.out.println(content);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }

            public void keyTyped(KeyEvent ev) {}
            public void keyReleased(KeyEvent ev) {}

        });
    }
}