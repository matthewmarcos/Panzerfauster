import java.net.*;

public class PanzerfausterPlayer{

    InetAddress address;
    String username;
    int x, y;
    float angle;

    public PanzerfausterPlayer(InetAddress address, String username, int x, int y, float angle){
        this.address = address;
        this.username = username;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void update(int x, int y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public String toString() {
        return this.username + " " + x + " " + y + " " + angle;
    }

    public InetAddress getAddress(){
        return this.address;
    }

    public String getUsername(){
        return this.username;
    }

}