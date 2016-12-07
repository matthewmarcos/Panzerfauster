package com.panzerfauster;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by matt on 11/11/16.
 * SINGLETON!!!!
 * This object sends the ArrayLists of tanks and projectiles data to the server
 */

public class GameState implements Runnable, InputProcessor {

    public static  int    port     = 4444;
    private static ArrayList<Tank>       tanks;
    private static ArrayList<Projectile> projectiles;
    private static GameState state = new GameState();
    private static String serverIP = "";
    private Tank           player;
    private String         username;
    private boolean        GAME_RUNNING = false; // RUNNING or NOT
    private DatagramSocket socket;
    private DatagramPacket packet;
    private InetAddress    address;
    private TextArea       chatBoxTextArea;
    private boolean        fired;
    private double lastUpdatedServer;


    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }


    private boolean gameStarted;


    private GameState() {
        this.tanks = new ArrayList<Tank>();
        this.projectiles = new ArrayList<Projectile>();
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
        return tanks;
    }


    public static ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }


    public static void addTank(Tank t) {
        tanks.add(t);
    }


    public static void addProjectile(Projectile p) {
        projectiles.add(p);
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

            }
        };

        Thread projectileSender = new Thread() {
            public void run() {
                while (true) {
                    //Listen for server for updates. Update the necessary arraylists
                    for(Projectile p : projectiles) {
                        try {
                            p.update();
                        }
                        catch(Exception e) {
                            //    Concurrent update
                        }

                    }
                    ArrayList<Projectile> temp = new ArrayList<Projectile>();

                    for(Projectile p : projectiles) {
                        if(p.isAlive()) {
                            temp.add(p);
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
