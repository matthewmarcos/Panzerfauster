import java.net.*;
import java.io.*;

public class PazerfausterServer implements Runnable {

    private ServerSocket serverSocket;

    public PazerfausterServer(int port) {
        System.out.println(port);
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(0);
    }

    public void run() {

    }
}