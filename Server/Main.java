import java.net.*;
import java.io.*;


public class Main extends Thread{
	private ServerSocket serverSocket;
	private String[] client = new String[20];

	public void server(int port) throws IOException{

		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
	}

	public void broadcast(String msg){
		for(int i=0; i<client.length();i++){
			String name=(String)client[i];
			send(name, msg);
		}
	}

	public void send(String player, String msg){
		byte buf[] = msg.getBytes();
		try{
			serverSocket.send(player, msg);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}

	}

	public void run(){
		while(true){

			byte[] buf = new byte[256];
			
			try{
				Socket server = serverSocket.accept();

				client.add(server.serverName);

				DataInputStream in = new DataInputStream(server.getInputStream());

				DataOutputStream out = new DataOutputStream(server.getOutputStream());

				server.close();
			}catch(SocketTimeoutException ste){
				break;
			}catch(IOException ioe){}


		}
	}

	public static void main(String[] args){

		try{
			int port = Integer.parseInt(args[0]);
			Thread t = new Main(port);
			t.start();
		}catch(IOException ioe){

		}catch(ArrayIndexOutOfBoundsException aioob){

		}
	}
}