import java.awt.*;
import javax.swing.*;

public class Client extends JFrame {

    public static final Dimension DIMENSION = new Dimension(800, 600);
    public static final String APP_NAME = new String("Panzerfauster");
    private static Container cont;
    private Game currentGame;

    public Client() throws Exception {
        super(APP_NAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Client.DIMENSION);

        cont = this.getContentPane();
        cont.setSize(Client.DIMENSION);
        currentGame = new Game();
        cont.add(currentGame);

        this.setVisible(true);
        // cont.removeAll();
    }
}