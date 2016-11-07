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

    private String message;

    private void initialize() {
        try {
            System.out.println(serverIP);
            System.out.println(port);
            conn = new Socket(serverIP, port);
            conn.setSoTimeout(0); //Do not close socket
            out = new DataOutputStream(
                conn.getOutputStream()
            );
            in = new  DataInputStream(
                conn.getInputStream()
            );
            this.chatbox = new Chatbox();
            this.chatbar = new Chatbar(this.chatbox, conn);
            chatbox.add("Connected to" + serverIP + ", port " + port);
        }
        catch (Exception e) {
            System.out.println("Failed initialize()");
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        this.setSize(Client.DIMENSION);
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
        while(conn.isConnected()) {
            try{
              message = in.readUTF(); //gets the message from server
              chatbox.add(message);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

      //  try {
            //conn.close();
        //}
        //catch (Exception e) {}
    }
}