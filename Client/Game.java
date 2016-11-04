import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Game extends JPanel implements Runnable{

    private Chatbox chatbox;
    private Chatbar chatbar;
    private String serverIP;
    private int port;

    private DataOutputStream out;
    private DataInputStream in;
    private Socket conn;

    private void initialize() {
        try {
            System.out.println(serverIP);
            System.out.println(port);
            conn = new Socket(serverIP, port);
            out = new DataOutputStream(
                conn.getOutputStream()
            );
            in = new  DataInputStream(
                conn.getInputStream()
            );
            chatbox.add("Connected to" + serverIP + ", port " + port);
        }
        catch (Exception e) {}


        this.setLayout(new BorderLayout());
        this.setSize(Client.DIMENSION);

        this.chatbox = new Chatbox();
        this.chatbar = new Chatbar(this.chatbox, out);
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
        // while(true) {
        // }

        try {
            conn.close();
        }
        catch (Exception e) {}
    }
}