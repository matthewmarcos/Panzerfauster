import java.net.*;
import java.io.*;
import java.util.*;

public class Connection implements Runnable {

    private Socket conn;
    private DataOutputStream out;
    private DataInputStream in;
    private PazerfausterServer server;
    private static ArrayList<Connection> connections = new ArrayList<Connection>();

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
        // Main listening for inputs
        String msg = null;
      //  System.out.println("running");
        while(conn.isConnected()) {
            System.out.println("Running!");
            try {
                msg = in.readUTF();
                System.out.println("MERON");
                
                if(msg == null) {
                  System.out.println("WALA!!!");
                    //continue;
                }
                
            }
            catch (Exception e) {
                System.out.println("Error reading");
                e.printStackTrace();
            }

            this.broadcast(msg);
             System.out.println("Continue");
        }

        System.out.println("Escaped main loop");

        if(conn.isConnected()) {
            System.out.println("Still connected");
        }
        else {
            System.out.println("Not connected!");
        }
    }

    public void write(String message) {
        try {
            out.writeUTF(message);
        }
        catch (Exception e) {
            System.out.println("error in writing");
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