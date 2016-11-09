import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// This is responsible for printing all its strings.
public class Chatbox extends JTextArea{

    private ArrayList<String> history;
    private String message;

    public Chatbox() {
        history = new ArrayList<String>();
        this.setEditable(false);
        this.redraw();
    }

    public void add(String s) {
        this.history.add(s);
        this.redraw();
    }

    private void redraw() {
        // Redraws the entire messageFields based on the chat history
        String content = new String("");
        for(String s : history) {
            content += s + "\n";
        }

        this.setText(content);
    }

}