import java.net.*;
import java.io.*;
import java.util.*;

public class Connection implements Runnable {

    private static ArrayList<Connection> connections = new ArrayList<Connection>();
    private static Map playerMap = new HashMap<String, PanzerfausterPlayer>();

    private Socket conn;
    private DataOutputStream out;
    private DataInputStream in;
    private PazerfausterServer server;
    private String username;
    private String ipAddress;
    private int port;
    private boolean tcp = true;

    private DatagramSocket socket;
    private DatagramPacket packet;

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
    private boolean udp = false;

    public Connection (
            Socket conn,
            DataOutputStream out,
            DataInputStream in,
            PazerfausterServer server
            
        ) {

        this.conn = conn;
        this.out = out;
        this.in = in;
        this.server = server;
        
    }

    public Connection (
            DatagramSocket socket,
            DatagramPacket packet,
            PazerfausterServer server
        ){
        
        this.socket = socket;
        this.packet = packet;
        this.server = server;
       
    }

    public void run(){

          //thread for chat(TCP)
            new Thread(new Runnable(){
                    Connection connect = new Connection(conn, out, in, server);
                    public void run(){
                    try{
                        // Getting username from client
                        username = in.readUTF();
                    }catch(IOException e){
                            // Handle connection issue
                            return;
                    }
                    catch(Exception e) {}

                    // Getting username from client
                     System.out.println(username + " has connected");

                    // Check if username exists in the server
                    for(Connection c : connections) {
                         if(username.equals(c.getUsername()) || username.equals("")) {
                            try{
                                 out.writeUTF("?fail");
                            } catch(Exception e) {}
                                return;
                            }
                    }

                    // Username does not exist in server
                    try{
                            out.writeUTF("?success");
                    } catch(Exception e) {}


                    // At this point,
                    broadcast(username + " has connected.\n");
                    connections.add(connect); //Eligible to receive broadcasts
                    Connection.printConnectedUsers();

                    // Main listening for inputs
                    String msg = null;
                    while(conn.isConnected()) {
                        try {
                            msg = in.readUTF();
                        }
                          
                        catch(Exception e) {
                                // Error in connection
                            System.out.println(username + " has disconnected");
                            Connection.removeConnection(connect);
                            broadcast(username + " has disconnected.\n");
                            Connection.printConnectedUsers();
                            break;
                        }

                        broadcast(username + ": " + msg + "\n");
                    }
                }
                }){

                }.start();
        

         //for UDP
            new Thread(new Runnable(){
                Connection udpConn = new Connection(socket, packet, server);
                public void run(){
                    byte[] buf = new byte[256];
                     String playerData=new String(buf);
                        
                        //remove excess bytes
                        playerData = playerData.trim();
                       
                        // process
                        switch(gameStage){
                              case WAITING:
                                    if (playerData.startsWith("?connect")){
                                        String tokens[] = playerData.split(" ");
                                        PanzerfausterPlayer player=new PanzerfausterPlayer(packet.getAddress(), tokens[1]);
                                        System.out.println("Player connected: "+tokens[1]);
                                        byte[] ipAddress = tokens[2].getBytes();
                                        //game.update(tokens[1].trim(),player);
                                        broadcast("?connected "+tokens[1]);
                                        playerCount++;
                                        System.out.println(playerCount);
                                        try{

                                        udpConn.addPlayer(tokens[1], new PanzerfausterPlayer(InetAddress.getByAddress(ipAddress), tokens[1]));
                                        }catch(Exception e){}
                                        if (playerCount >= minNumPlayers){
                                            gameStage=GAME_START;
                                        }
                                    }
                                  break;    
                              case GAME_START:
                                  System.out.println("Game State: START");
                                  broadcast("START");
                                  gameStage=IN_PROGRESS;
                                  break;
                              case IN_PROGRESS:
                                  //System.out.println("Game State: IN_PROGRESS");
                                  
                                  //Player data was received!
                                  if (playerData.startsWith("PLAYER")){
                                      //Tokenize:
                                      //The format: PLAYER <player name> <x> <y>
                                      String[] playerInfo = playerData.split(":");                    
                                      String pname =playerInfo[1];
                                      int x = Integer.parseInt(playerInfo[2].trim());
                                      int y = Integer.parseInt(playerInfo[3].trim());
                                      //Get the player from the game state
                                      PanzerfausterPlayer player=(PanzerfausterPlayer)udpConn.getPlayers().get(pname);                   
                                      player.setX(x);
                                      player.setY(y);
                                      //Update the game state
                                    playerMap.put(pname, player);
                                      //Send to all the updated game state
                                      broadcast(udpConn.toString());
                                  }
                                  break;
                        }                 
                }

            }){
        }.start();   
      
        
    }


        
    public void write(String message) {
        try {
            out.writeUTF(message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) {
        // Send messages to out
        try {
            for(Connection c : connections) {
                c.write(message);
            }

        }
        catch (Exception e) {
            System.out.println("Error in broadcasting");
            e.printStackTrace();
        }
    }

    public static ArrayList<Connection> getConnections(){
        return connections;
    }

    public static int getNumOfConnections(){
        return connections.size();
    }
    public Socket getSocket(){
        return conn;
    }

    private String getUsername(){
        return this.username;
    }

    public static Map getPlayers(){
        return playerMap;
    }

    public void addPlayer(String username, PanzerfausterPlayer player){
        playerMap.put(username, player);

    }

    public static void removeConnection(Connection c){
        connections.remove(c);
    }

    public static void printConnectedUsers() {
        int i = 1;

        System.out.println("Connected users: " + connections.size());

        for(Connection c : connections) {
            System.out.println((i++) +") " + c.getUsername());
        }
    }

    public String toString(){
        String retval="";
        for(Iterator ite=playerMap.keySet().iterator();ite.hasNext();){
            String name=(String)ite.next();
            PanzerfausterPlayer player=(PanzerfausterPlayer)playerMap.get(name);
            retval+=player.toString()+":";
        }
        return retval;
    }
}
