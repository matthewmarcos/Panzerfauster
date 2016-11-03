import java.awt.*;
import javax.swing.*;

public class Game extends JPanel {

    private Chatbox chatbox;
    private Chatbar chatbar;

    private void initialize() {
        this.setLayout(new BorderLayout());
        this.setSize(Client.DIMENSION);

        this.chatbox = new Chatbox();
        this.chatbar = new Chatbar(this.chatbox);
        this.add(chatbar, BorderLayout.SOUTH);
    }

    public Game() {
        super();
        initialize();
    }
}