
import java.net.*;
import java.io.*;
import java.util.*;

public class PazerfausterServer implements Runnable {


    private ServerSocket serverSocket;

    private DataOutputStream out;
    private DataInputStream in;
    private int port;
    private ArrayList<Thread> clients;
    private HashMap<String, Connection> connections;

    private final int PORT = 4444;
    private DatagramSocket socket;
    private String playerData;
    private int playerCount = 0;



    public PazerfausterServer(int port) {

        this.port = port;
        this.clients = new ArrayList<Thread>();

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(0);
        }
        catch (Exception e) {}

    }

    public void run() {

        // @TODO: CHANGE LOOP CONDITION
        while(true) {
            try {
                // Listen for connections

                System.out.println("Listening to port: " + port);
                Socket server = serverSocket.accept();
                server.setSoTimeout(0);

                // Getting input and output streams of client
                out = new DataOutputStream(
                    server.getOutputStream()
                );

                in = new DataInputStream(
                    server.getInputStream()
                );

                // New Connection object
                Connection newConn = new Connection(server, out, in, this);
                Thread temp = new Thread(newConn);
                temp.start();
                clients.add(temp);


            }
            catch (Exception e) {}
        }
    }

    public void broadcastText(String f) {
        try{
            for(Iterator ite=connections.keySet().iterator();ite.hasNext();){
                String name=(String)ite.next();
                Connection c = (Connection)connections.get(name);
                c.write(f);
            }
         }
         catch(Exception e){
            e.printStackTrace();
         }
    }
}
