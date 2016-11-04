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
        while(true) {
            // System.out.println();
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

    private Connection(){}
}