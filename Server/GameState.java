import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameState {

    private static GameState state = new GameState();
    private static boolean isRunning = false;
    private static DatagramSocket serverSocket = null;
    private static HashMap<String, PanzerfausterPlayer> playerData;

    public static GameState getState() {
        return state;
    }

    public static void startUDPServer() {
        if(isRunning) {
            System.out.println("The game has already started");
            return;
        }

        try{
            serverSocket = new DatagramSocket(4444);
        }
        catch(Exception e) {
            System.out.println("Error creating DatagramSocket");
        }


        new Thread(new Runnable() {
            public void run() {

                String playerData = "";

                while(true){
                    // Get the data from players
                    byte[] buf = new byte[256];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    try{
                        serverSocket.receive(packet);
                    }catch(Exception ioe){}

                    /**
                    * Convert the array of bytes to string
                    */
                    playerData=new String(buf);

                    //remove excess bytes
                    playerData = playerData.trim();
                    if(!playerData.equals("")) {
                        parsePlayerData(playerData, packet);
                        broadcastUDP();
                    }

                }
            }
        }){}.start();
    }

    private static void parsePlayerData(String playerDataIn, DatagramPacket packet) {
        // System.out.println(playerDataIn);
        String[] data = playerDataIn.split(" ");
        if(data.length == 6) {
            // Fired
        }
        String username = data[1];
        int x = Integer.parseInt(data[2]);
        int y = Integer.parseInt(data[3]);
        float angle = Float.parseFloat(data[4]);

        PanzerfausterPlayer p = playerData.get(username);

        p.update(x, y, angle);
        // System.out.println(p.toString());

        playerData.put(username, p);

    }

    public static void broadcastUDP() {
        // Broadcast game updates
        String msg = "";
        ArrayList<PanzerfausterPlayer> players = new ArrayList<PanzerfausterPlayer>(playerData.values());
        for(PanzerfausterPlayer p : players) {
            msg += p.toString() + " | ";
        }

        // System.out.println(msg);

    }

    private GameState() {
        playerData = new HashMap<String, PanzerfausterPlayer>();

        for(Iterator ite=Connection.getConnections().keySet().iterator();ite.hasNext();){
            String name=(String)ite.next();
            playerData.put(name, new PanzerfausterPlayer(null, name, 0, 0, 0f));
        }

    }
}