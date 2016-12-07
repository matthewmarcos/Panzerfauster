import java.net.*;

public class PanzerfausterPlayer{

    InetAddress address;

    String username;
    int x, y, port;
    float angle;

    public PanzerfausterPlayer(InetAddress address, String username, int x, int y, float angle){
        this.address = address;
        this.username = username;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void update(int x, int y, float angle, DatagramPacket p) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.port = p.getPort();
        this.address = p.getAddress();
    }

    public String toString() {
        return this.username + "&" + x + "&" + y + "&" + angle;
    }

    public String getUsername(){
        return this.username;
    }

    public int getPort() {
        return this.port;
    }

    public InetAddress getAddress() {
        return this.address;
    }

}