import java.net.*;
import java.io.*;
import java.util.*;

public class Panzerfauster{

    private InetAddress address;
    private String name;
    private final int PORT = 4438;
    private int x, x, angle;

  /*  private DatagramSocket datagramSocket;
    private Pazerfauster game;
    private PanzerfausterGameServer server;
    private static ArrayList<PanzerfausterPlayers> players = new ArrayList<PanzerfausterPlayers>();*/

    public PanzerfausterPlayers (
            PanzerfausterServer server,
            String address,
            String name
        ) {

        this.datagramSocket = socket;
        this.server = server;
        this.address = address;
        this.name = name;

        game = new Panzerfauster();
        server = new PanzerfausterGameServer();
    }

     public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getAngle(){
        return angle;
    }

    public String getAddress(){
        return address;
    }

    public String getName(){
        return name;
    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public void getPlayer(){
        String value = " ";
        value+=name+" ";
        value+=x+" ";
        value+=y+" ";
        value+=angle+" ";
        return value;
    }
}