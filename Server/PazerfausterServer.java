package com.panzerfauster;


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


    public PazerfausterServer(int port) {

        this.port = port;
        this.clients = new ArrayList<Thread>();

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(0);
        }
        catch (Exception e) {}

    }

    public void run() {
        System.out.println("Listening to port: " + port);
        while(true) {
            try {
                // Listen for connections
                Socket server = serverSocket.accept();
                server.setSoTimeout(0);
                System.out.println("A client has connected!");

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
}