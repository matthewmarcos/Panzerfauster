import java.net.*;
import java.io.*;

public class PazerfausterServer implements Runnable {

    private ServerSocket serverSocket;

    private DataOutputStream out;
    private DataInputStream in;

    private int port;

    public PazerfausterServer(int port) {
        this.port = port;

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(0);
        }
        catch (Exception e) {}

    }

    public void run() throws Exception {
        System.out.println("Listening to port: " + port);

        Socket server = serverSocket.accept();

        out = new DataOutputStream(
            server.getOutputStream()
        );

        in = new new DataInputStream(
            server.getInputStream()
        );

        while(true) {
            try {

            }
        }
    }
}