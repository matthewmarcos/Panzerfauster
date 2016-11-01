import java.awt.*;
import javax.swing.*;

public class Client extends JFrame {

    public static final Dimension DIMENSION = new Dimension(800, 600);
    private static Container cont;
    private Game currentGame;

    public Client() throws Exception {
        super("TankDerby");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Client.DIMENSION);
        this.setVisible(true);
        cont = this.getContentPane();
        cont.setSize(Client.DIMENSION);

        currentGame = new Game();

        cont.removeAll();
        cont.add(currentGame);
    }
}