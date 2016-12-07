package com.panzerfauster;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by matt on 11/11/16.
 * SINGLETON!!!!
 * This object sends the ArrayLists of tanks and projectiles data to the server
 */

public class GameState implements Runnable, InputProcessor {

    public static  int    port     = 4444;
    private static HashMap<String, Tank> tanks;
    private static HashMap<String, Projectile> projectiles;
    private static int idCounter = 0;
    private static GameState state = new GameState();
    private static String serverIP = "";
    private Tank           player;
    private boolean        GAME_RUNNING = false; // RUNNING or NOT
    private DatagramSocket socket;
    private DatagramPacket packet;
    private InetAddress    address;
    private TextArea       chatBoxTextArea;
    private boolean        fired;
    private double lastUpdatedServer;
    private String username = "";


    public void setUsername(String username) {
        this.username = username;
    }


    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }


    private boolean gameStarted;


    private GameState() {
        this.tanks = new HashMap<String, Tank>();
        this.projectiles = new HashMap<String, Projectile>();
        try {
            socket = new DatagramSocket();
            System.out.println("Created datagram socket");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static void setServerIP(String serverIP) {
        GameState.serverIP = serverIP;
    }


    public static GameState getState() {
        return state;
    }


    public static ArrayList<Tank> getTanks() {
        ArrayList<Tank> tanksList = new ArrayList<Tank>(tanks.values());;
        return tanksList;
    }


    public static ArrayList<Projectile> getProjectiles() {
        ArrayList<Projectile> projectileList = new ArrayList<Projectile>(projectiles.values());
        return projectileList;
    }


    public void addTank(String playerName, Tank t) {
        tanks.put(playerName + " tank "+ (idCounter++) + "", t);
    }


    public void addProjectile(Projectile p) {
        projectiles.put(this.username + "projectile " +(idCounter++) + "", p);
    }


    public Tank getPlayer() {
        return player;
    }


    public void setPlayer(Tank player) {
        this.player = player;
    }


    public String getUsername() {
        return username;
    }


    public boolean isGAME_RUNNING() {
        return GAME_RUNNING;
    }


    public void setGAME_RUNNING(boolean b) {
        this.GAME_RUNNING = b;
    }


    public void startGame() {
        Thread t = new Thread(this);
        t.start();
    }


    public void run() {
        //Create threads that talk to the server.

        final Thread playerSender = new Thread() {

            public void run() {

            }
        };

        Thread playerListener = new Thread() {
            public void run() {
                String serverData;
                while(true){
                    try{
                        Thread.sleep(1);
                    }catch(Exception ioe){}

                    //Get the data from players
                    byte[] buf = new byte[256];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    try{
                        socket.receive(packet);
                    }catch(Exception ioe){/*lazy exception handling :)*/}

                    serverData=new String(buf);
                    serverData=serverData.trim();

                    if (!serverData.equals("")){
                        System.out.println("Server Data:" +serverData);
                    }


                }
            }
        };

        Thread projectileSender = new Thread() {
            public void run() {
                while (true) {

                    for(Iterator ite = projectiles.keySet().iterator(); ite.hasNext();){
                        String name=(String)ite.next();
                        Projectile p = (Projectile)projectiles.get(name);
                        try {
                            p.update();
                        }
                        catch(Exception e) {
                            //    Concurrent update
                        }
                    }

                    HashMap<String, Projectile> temp = new HashMap<String, Projectile>();

                    for(Iterator ite = projectiles.keySet().iterator(); ite.hasNext();){
                        String name=(String)ite.next();
                        Projectile p = (Projectile)projectiles.get(name);
                        if(p.isAlive()) {
                            temp.put(name, p);
                        }
                    }

                    projectiles = temp;

                    try {
                        Thread.sleep(25);
                    }
                    catch(Exception e) {

                    }
                }
            }
        };

        Thread projectileListener = new Thread() {
            public void run() {

            }
        };

        playerSender.start();
        playerListener.start();
        projectileSender.start();
        projectileListener.start();

    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE) {
            Panzerfauster.getInstance().setMainMenuScreen();
        }
        return true;
    }


    @Override
    public boolean keyUp(int keycode) {
        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // This function fires when the user clicks on the screen.
        // The player fires a projectule in the direction it is facing
        this.player.fire();

        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        updateServer();
        return false;
    }


    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    public void updateServer() {

        if(!gameStarted) return;

        double timeSinceLastUpdate = System.currentTimeMillis() - lastUpdatedServer;
        if(timeSinceLastUpdate < 15) {
            return;
        }

        lastUpdatedServer = System.currentTimeMillis();

        String msg;
        int x, y;
        float angle;
        x = player.getXcoord();
        y = player.getYcoord();
        angle = player.getAngle();

        msg = "Player " + username;
        msg += " " + x + " " + y + " " + angle;
        if(player.isFired()) {
            msg += " fired";
            player.setFired(false);
        }

        System.out.println(msg);

        try {
            byte[] buf = msg.getBytes();
            InetAddress address = InetAddress.getByName(GameState.serverIP);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, GameState.port);
            socket.send(packet);
        }
        catch(Exception e) {
        }

    }


    public void fire() {
        this.fired = true;
    }
}
