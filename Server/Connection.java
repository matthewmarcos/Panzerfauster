import java.net.*;
import java.io.*;

public class Connection implements Runnable {

    private Socket conn;
    private DataOutputStream out;
    private DataInputStream in;

    public Connection (Socket conn, DataOutputStream out, DataInputStream in) {
        this.conn = conn;
        this.out = out;
        this.in = in;
    }

    public void run() {
        // Main listening for inputs
        String msg = null;
        System.out.println("running");
        while(conn.isConnected()) {
            System.out.println("Rinning!");
            try {
                msg = in.readUTF();
                if(msg == null) {
                    continue;
                }
            }
            catch (Exception e) {
                System.out.println("Error reading");
                e.printStackTrace();
            }

            System.out.println("Sabi nya: " + msg);

            write(msg);
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
        // Send messages to out
        try {
            out.writeUTF(message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}