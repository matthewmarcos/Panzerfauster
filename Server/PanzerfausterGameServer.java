import java.net.*;
import java.io.*;
import java.util.*;

public class PanzerfausterGameServer implements Runnable{

    // private int port;
    // private ArrayList<Thread> clients;
    // private ArrayList<Connection> connections;
    // private DatagramSocket datagramSocket;
    // private boolean connected = true;
    // private InetAddress inetAddress = new InetAddress("200.0.0.1");
    // private SocketAddress groupAddress = new InetSocketAddress(inetAddress, port);

    // public PanzerfausterGameServer(int port) {

    //     this.port = port;
    //     this.clients = new ArrayList<Thread>();
    //     try{
    //        datagramSocket = new DatagramSocket(port);
    //    }catch(Exception e){}
    // }


    public void run(){
    //     System.out.println("Listening to port: " + port);
    //     while(connected) {
    //         try {
    //             DatagramPacket packet;

    //             byte[] buf = new byte[256];

    //             datagramSocket.receive(packet);
    //             System.out.println(buf);

    //             // send the response to the client at "address" and "port"
    //             InetAddress group = packet.setSocketAddress(groupAddress);
    //             int port = packet.getPort();
    //             packet = new DatagramPacket(buf, buf.length, group, port);
    //             datagramSocket.send(packet);
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //             connected = false;
    //         }
    //     }
    //     datagramSocket.close();
    }
}