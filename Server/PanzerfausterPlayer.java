import java.net.*;

public class PanzerfausterPlayer{

    InetAddress address;
    String username;
    int x, y;
    float angle;

    public PanzerfausterPlayer(InetAddress address, String username){
        this.address = address;
        this.username = username;
        this.x = 0;
        this.y = 0;
        this.angle = 0;
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