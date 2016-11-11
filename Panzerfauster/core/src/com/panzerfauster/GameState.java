package com.panzerfauster;

import java.util.ArrayList;

/**
 * Created by matt on 11/11/16.
 *
 * SINGLETON!!!!
 *
 * This object sends the ArrayLists of tanks and projectiles to the server
 * (Thread)
 *
 * `This object can print the
 */
public class GameState {

    private static ArrayList<Tank> tanks;
    private static ArrayList<Projectile> projectiles;
    private static GameState state = new GameState();

    private GameState() {
        this.tanks = new ArrayList<Tank>();
        this.projectiles = new ArrayList<Projectile>();

        this.tanks.add(new Tank("sprites/Tank.png", false, "Tank", 0, 10, 5, 0));
        this.tanks.add(new Tank("sprites/Tank.png", false, "Tank", 300, 10, 5, 90));
        this.tanks.add(new Tank("sprites/Tank.png", false, "Tank", -100, 2000, 5, 0));
    }

    public static GameState getState () {
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

    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }
}
