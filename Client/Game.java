import java.awt.*;
import javax.swing.*;

public class Game extends JPanel {

    private void initialize() {
        this.setLayout(new BorderLayout());
        this.setSize(Client.DIMENSION);

        System.out.println("Set up the values of the game here.");
        JTextField chatbar = new JTextField();
        this.add(chatbar, BorderLayout.SOUTH);
    }

    public Game() {
        super();
        initialize();
    }
}