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

    final int PORT = 4500;
    DatagramSocket datagramSocket;
    private static GameState curState;
    private int numPLayers;

    private int[] buf;
    private String playerData;


    public PazerfausterServer(int port) {

        this.port = port;
        this.clients = new ArrayList<Thread>();

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(0);

            datagramSocket = new DatagramSocket(PORT);
        }
        catch (Exception e) {}

    }

    public void run() {
        System.out.println("Listening to port: " + port);
        while(true) {

            byte[] buf = new byte[256];

             DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try{
                datagramSocket.receive(packet);
            }catch(Exception ioe){}

            playerData=new String(buf);
            playerData = playerData.trim();

            String[] playerInfo = playerData.split(" ");
            String pname =playerInfo[1];
            int x = Integer.parseInt(playerInfo[2].trim());
            int y = Integer.parseInt(playerInfo[3].trim());
            //Get the player from the game state
            NetPlayer player=(NetPlayer)game.getPlayers().get(pname);
            player.setX(x);
            player.setY(y);
            //Update the game state
            game.update(pname, player);
            //Send to all the updated game state
            broadcastGame(curState.toString());

            try {
                // Listen for connections
                Socket server = serverSocket.accept();
                server.setSoTimeout(0);
                System.out.println("A client has connected!");

                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                try{
                    datagramSocket.receive(packet);
                }catch(Exception ioe){}

                playerData=new String(buf);
                playerData = playerData.trim();

                // Getting input and output streams of client
                out = new DataOutputStream(
                    server.getOutputStream()
                );
                in = new DataInputStream(
                    server.getInputStream()
                );

                Connection newConn = new Connection(server, out, in, this);
                Thread temp = new Thread(newConn);
                temp.start();
                clients.add(temp);

            }
            catch (Exception e) {}
        }
    }

    public void broadcastText(String f) {
        try{
            for(Connection c : this.connections) {
            // for(Connection c = connections.next() ; connections.hasNext) {
                c.write(f);
            }
         }catch(Exception e){
            e.printStackTrace();
         }
    }

    public broadcastGame(Gamestate curState){
        for(Iterator i = )
            datagramSocket.sendGame(player, msg);
    }

    public sendGame(Player player, String msg){
        DatagramPacket packet;
        byte buf[] = msg.getBytes();
        packet = new DatagramPacket(buf, buf.length, player.getAddress(),player.getPort());
        try{
            serverSocket.send(packet);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}