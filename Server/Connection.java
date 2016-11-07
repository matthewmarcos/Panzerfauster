import java.net.*;
import java.io.*;

public class Connection implements Runnable {

    private Socket conn;
    private DataOutputStream out;
    private String in;

    public Connection (Socket conn, DataOutputStream out, String in) {
        this.conn = conn;
        this.out = out;
        this.in = in;
    }

    public void run() {
        // Main listening for inputs
        String msg;
        System.out.println("running");
        while(conn.isConnected()) {
            //System.out.println("LISTENING!");
            System.out.println(in);
            write(in);
            
        }

    }

    public void write(String message) {
        // Send messages to out
        try {
            out.writeUTF(message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}