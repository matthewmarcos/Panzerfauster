import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Game extends JPanel implements Runnable{

    private Chatbox chatbox;
    private Chatbar chatbar;
    private String serverIP;
    private int port;
    private String name;

    private DataOutputStream out;
    private DataInputStream in;
    private Socket conn;

    private String message;

    private PanzerfausterPlayer player;
    private DatagramSocket datagramSocket = new DatagramSocket();

    private void initialize() {

        player = new PanzerfausterPlayer(serverIP, name)
        
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
        this.name = name;

        initialize();
    }

    public void run() {
        // Update chatbox from server here
        while(conn.isConnected()) {

            //UDP
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try{
                socket.receive(packet);
            }catch(Exception ioe){/*lazy exception handling :)*/}
            
            serverData=new String(buf);
            serverData=serverData.trim();

            //TCP
            try{
              message = in.readUTF(); //gets the message from server
              chatbox.add(message);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void send(String msg){
        try{
            byte[] buf = msg.getBytes();
            InetAddress address = InetAddress.getByName(server);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
            socket.send(packet);
        }catch(Exception e){}
        
    }
}