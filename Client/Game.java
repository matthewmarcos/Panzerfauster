import java.awt.*;
import javax.swing.*;

public class Game extends JPanel {

    private void initialize() {
        System.out.println("Set up the values of the game here.");
    }

    public Game() {
        super();
        this.setLayout(null);
        this.setSize(Client.DIMENSION);
        initialize();
    }
}