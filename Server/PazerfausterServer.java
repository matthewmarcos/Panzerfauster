import java.net.*;
import java.io.*;

public class PazerfausterServer implements Runnable {

    private ServerSocket serverSocket;
    private int port;

    public PazerfausterServer(int port) {
        this.port = port;

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(0);
        }
        catch (Exception e) {}

    }

    public void run() {
        System.out.println("Listening to port: " + port);

        while(true) {
            try {
                Socket client = serverSocket.accept();
                client.getOutputStream();
            }
        }
    }
}