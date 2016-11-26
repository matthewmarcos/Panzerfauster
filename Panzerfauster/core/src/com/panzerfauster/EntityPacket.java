package com.panzerfauster;

import java.util.ArrayList;

/**
 * Created by matth on 11/25/2016.
 */
public class EntityPacket {

    protected ArrayList<TankData>       tankData;
    protected ArrayList<ProjectileData> projectileData;
    protected String                    username;


    public EntityPacket(ArrayList<TankData> tankData, ArrayList<ProjectileData> projectileData, String username) {
        this.tankData = tankData;
        this.projectileData = projectileData;
        this.username = username;
    }


    private EntityPacket() {
    }


    public ArrayList<TankData> getTankData() {

        return tankData;
    }


    public ArrayList<ProjectileData> getProjectileData() {

        return projectileData;
    }


    public String getUsername() {

        return username;
    }
}
