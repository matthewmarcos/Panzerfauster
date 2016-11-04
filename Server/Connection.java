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
        String msg ="";
        try{
          msg = in.readUTF();
           System.out.println(msg);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        while(msg != "") {
             write(msg);
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