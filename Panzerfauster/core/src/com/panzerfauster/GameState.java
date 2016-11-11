package com.panzerfauster;

import java.util.ArrayList;

/**
 * Created by matt on 11/11/16.
 *
 * This object sends the ArrayLists of tanks and projectiles to the server
 * (Thread)
 *
 * This object can print the
 */
public class GameState {

    private ArrayList<Tank> tanks;
    private ArrayList<Projectile> projectiles;

    public GameState() {
        this.tanks = new ArrayList<Tank>();
        this.projectiles = new ArrayList<Projectile>();

        this.tanks.add(new Tank("sprites/Tank.png", false, "Tank", 0, 10, 5, 0));
        this.tanks.add(new Tank("sprites/Tank.png", false, "Tank", 300, 10, 5, 0));
        this.tanks.add(new Tank("sprites/Tank.png", false, "Tank", -100, 2000, 5, 0));
    }

    public ArrayList<Tank> getTanks() {
        return this.tanks;
    }

    public ArrayList<Projectile> getProjectiles() {
        return this.projectiles;
    }

}
