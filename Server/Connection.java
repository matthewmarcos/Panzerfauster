import java.net.*;
import java.io.*;
import java.util.*;

public class Connection implements Runnable {

    private static ArrayList<Connection> connections = new ArrayList<Connection>();

    private Socket conn;
    private DataOutputStream out;
    private DataInputStream in;
    private PazerfausterServer server;
    private String username;
    private String ipAddress;
    private int port;

    public Connection (
            Socket conn,
            DataOutputStream out,
            DataInputStream in,
            PazerfausterServer server
        ) {

        this.conn = conn;
        this.out = out;
        this.in = in;
        this.server = server;
        connections.add(this);
    }

    public void run() {

        // Kuha ng name
        try {
            out.writeUTF("SDASDASDSD");
        }
        catch(Exception e) {}
        // If existing na ang name, sibakin.

        // Main listening for inputs
        String msg = null;
        while(conn.isConnected()) {
            try {
                msg = in.readUTF();
            }
            catch (IOException e) {
                // Connection closed
                e.printStackTrace();
                // Delete this connection
                break;
            }
            catch(Exception e) {
                continue;
            }

            this.broadcast(msg);
        }

    }

    public void write(String message) {
        try {
            out.writeUTF(message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) {
        // Send messages to out
        try {
            for(Connection c : connections) {
                c.write(message);
            }

        }
        catch (Exception e) {
            System.out.println("Error in broadcasting");
            e.printStackTrace();
        }
    }

    public static ArrayList<Connection> getConnections(){
        return connections;
    }

    public Socket getSocket(){
        return conn;
    }

}
