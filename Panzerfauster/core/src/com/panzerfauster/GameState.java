package com.panzerfauster;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

/**
 * Created by matt on 11/11/16.
 * SINGLETON!!!!
 * This object sends the ArrayLists of tanks and projectiles data to the server
 */

public class GameState implements Runnable, InputProcessor {

    private static ArrayList<Tank>       tanks;
    private static ArrayList<Projectile> projectiles;
    private static GameState state = new GameState();
    private Tank    player;
    private String  username;
    private boolean GAME_RUNNING; // RUNNING or NOT
    private DatagramSocket socket;
    private DatagramPacket packet;
    private static ArrayList<TankData> tankData;
    private static ArrayList<ProjectileData> projectileData;
    private InetAddress address;




    private GameState() {
        this.tanks = new ArrayList<Tank>();
        this.projectiles = new ArrayList<Projectile>();
        this.GAME_RUNNING = false;
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

        Thread playerSender = new Thread() {
            public void run() {
                EntityPacket entity = new EntityPacket(tankData, projectileData, username);

                tankData = entity.getTankData();
                String msg = tankData.toString();

                try{

                    MulticastSocket socket = new MulticastSocket(4446);
                    InetAddress group = InetAddress.getByName("200.0.0.1");
                    socket.joinGroup(group);

                    byte[] buf = new byte[256];

                    for (int i = 0; i < 5; i++) {
                        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4438);
                        socket.send(packet);
                        // get response
                        packet = new DatagramPacket(buf, buf.length);
                        socket.receive(packet);

                        // display response
                        String received = new String(packet.getData());
                        System.out.println(received);
                    }

                }catch(Exception e){}






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
        return false;
    }


    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
