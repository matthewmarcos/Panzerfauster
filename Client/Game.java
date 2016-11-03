import java.awt.*;
import javax.swing.*;

public class Game extends JPanel implements Runnable{

    private Chatbox chatbox;
    private Chatbar chatbar;
    private String serverIP;
    private int port;

    private void initialize() {

        DataOutputStream out;
        DataInputStream in;

        try {
            Socket conn = new Socket(hostName, portNumber);
            out = new DataOutputStream(
                client.getOutputStream()
            );
            in = new new DataInputStream(
                client.getInputStream()
            );
        }
        catch (Exception e) {}
        finally {
            chatbox.add("Connected to" + hostName + ", port " + portNumber);
        }


        this.setLayout(new BorderLayout());
        this.setSize(Client.DIMENSION);

        this.chatbox = new Chatbox();
        this.chatbar = new Chatbar(this.chatbox);
        this.add(chatbar, BorderLayout.SOUTH);
        this.add(chatbox, BorderLayout.NORTH);
    }

    public Game(String serverIP, int port, String name) {
        super();

        this.serverIP = serverIP;
        this.port = port;

        initialize();
    }

    public void run() {
        // Update chatbox from server here
        while(true) {
            System.out.println(in.readUTF());
        }
    }
}