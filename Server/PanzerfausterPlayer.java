import java.net.*;

public class PanzerfausterPlayer{
	
	InetAddress address;
	String name;

	public PanzerfausterPlayer(InetAddress address, String name){
		this.address = address;
		this.name = name;
	}

	public InetAddress address(){
		return this.address;
	}

	public String name(){
		return this.name;
	}

	


}