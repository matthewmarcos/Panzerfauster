import java.net.*;

public class PanzerfausterPlayer{
	
	InetAddress address;
	String name;
	int x, y;
	float angle;

	public PanzerfausterPlayer(InetAddress address, String name){
		this.address = address;
		this.name = name;
	}

	public InetAddress getAddress(){
		return this.address;
	}

	public String getName(){
		return this.name;
	}


	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public void setAngle(float angle){
		this.angle = angle;
	}

	public int getX(){
		return this.x;
	}


	public int getY(){
		return this.y;
	}

	
	public float getAngle(){
		return this.angle;
	}

	public String toString(){
		String retval="";
		retval+="?player ";
		retval+=name+" ";
		retval+=x+" ";
		retval+=y+" ";
		retval+=angle;
		return retval;
	}	


}