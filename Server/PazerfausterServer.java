
import java.net.*;
import java.io.*;
import java.util.*;

public class PazerfausterServer implements Runnable {


    private ServerSocket serverSocket;

    private DataOutputStream out;
    private DataInputStream in;
    private int port;
    private ArrayList<Thread> clients;
    private ArrayList<Connection> connections;

    private final int PORT = 4444;
    public static final int GAME_START=0;
    public static final int IN_PROGRESS=1;
    public final int GAME_END=2;
    public final int WAITING=3;



    private DatagramSocket datagramSocket;
    private String playerData;
    private int playerCount = 0;
    private int gameStage = WAITING;
    private int minNumPlayers = 3;



    public PazerfausterServer() {

        this.port = 8000;
        this.clients = new ArrayList<Thread>();

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(0);

            datagramSocket = new DatagramSocket(4444);
            datagramSocket.setSoTimeout(100);

        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444");
            e.printStackTrace();

        }catch(Exception e){}
        

    }

    public void run() {


        //chat Thread
        new Thread(new Runnable() { 
            PazerfausterServer pazerfausterServer;
            public void run(){
                

                // @TODO: CHANGE LOOP CONDITION
                 while(true) {
                    try {
                        // Listen for connections

                        System.out.println("Listening to port: " + port);
                        Socket server = serverSocket.accept();
                        server.setSoTimeout(0);

                        // Getting input and output streams of client
                        out = new DataOutputStream(
                            server.getOutputStream()
                        );

                        in = new DataInputStream(
                            server.getInputStream()
                        );

                        // New Connection object
                        Connection newConn = new Connection(server, out, in, pazerfausterServer);
                        Thread temp = new Thread(newConn);
                        temp.start();
                        clients.add(temp);
                    }
                    catch (Exception e) {}
                }
            }

        }){

        }.start();


        //UDP Thread
        new Thread(new Runnable(){
            PazerfausterServer pazerfausterServer;
            public void run(){
                System.out.println("UDP\n");

                while(true){
                        
                    // Get the data from players
                    byte[] buf = new byte[256];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    try{
                        datagramSocket.receive(packet);
                    }catch(Exception ioe){}
                    
                    Connection udpConn = new Connection(datagramSocket, packet, pazerfausterServer);
                

                }
            }                       
        }){

        }.start();
       
    }

    public void broadcastText(String f) {
        try{
            for(Connection c : this.connections) {
                c.write(f);
            }
         }catch(Exception e){
            e.printStackTrace();
         }
    }

    public void broadcast(String msg){
        for(Iterator ite=Connection.getPlayers().keySet().iterator();ite.hasNext();){
            String name=(String)ite.next();
            PanzerfausterPlayer player=(PanzerfausterPlayer)Connection.getPlayers().get(name);            
            send(player,msg);   
        }
    }

    public void send(PanzerfausterPlayer player, String msg){
        DatagramPacket packet;  
        byte buf[] = msg.getBytes();        
        packet = new DatagramPacket(buf, buf.length, player.getAddress(),4444);
        try{
            datagramSocket.send(packet);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
