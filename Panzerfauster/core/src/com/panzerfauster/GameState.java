package com.panzerfauster;

import java.util.ArrayList;

/**
 * Created by matt on 11/11/16.
 * <p>
 * SINGLETON!!!!
 * <p>
 * This object sends the ArrayLists of tanks and projectiles to the server
 * (Thread)
 * <p>
 * `This object can print the
 */

public class GameState implements Runnable {

    private static ArrayList<Tank>       tanks;
    private static ArrayList<Projectile> projectiles;
    private static GameState state = new GameState();


    private GameState() {
        this.tanks = new ArrayList<Tank>();
        this.projectiles = new ArrayList<Projectile>();
        Thread t = new Thread(this);

        t.start();
    }


    public void run() {
        while (true) {
            for(Projectile p : projectiles) {
                p.update();
            }

            // Remove projectiles that die
            // projectiles = projectiles.stream().filter(p -> p.isAlive()).collect(Collectors.toList());
            ArrayList<Projectile> temp = new ArrayList<Projectile>();

            for(Projectile p : projectiles) {
                if(p.isAlive()) {
                    temp.add(p);
                }
            }

            projectiles = temp;

            try {
                Thread.sleep(50);
            }
            catch(Exception e) {

            }
        }
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

}
