import java.net.*;
import java.io.*;
import java.util.*;


public class PanzerfausterGameServer implements Runnable{

    private int port;
    private ArrayList<Thread> clients;
    private ArrayList<Connection> connections;
    private DatagramSocket datgramSocket;

	 public PanzerfausterGameServer(int port) {

        this.port = port;
        this.clients = new ArrayList<Thread>();
        datagramSocket = new DatagramSocket(port);
    }


    public void run(){
    	  System.out.println("Listening to port: " + port);
        while(true) {
            try {
                
                DatagramPacket packet = new DatagramPacket(port);
                try{
                	datagramSocket.receive(packet);

                }catch(Excpetion e){}
                
                

                

            }
            catch (Exception e) {}
        }

    }
}