package com.panzerfauster;

/**
 * Created by matt on 11/22/16.
 */

public class ProjectileData {

    private String id, owner;
    private int projectileDamage, xcoord, ycoord;
    private float speed, angle;


    public ProjectileData(String id, String owner, int projectileDamage, int xcoord, int ycoord, float speed,
                          float angle) {
        this.id = id;
        this.owner = owner;
        this.projectileDamage = projectileDamage;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.speed = speed;
        this.angle = angle;
    }


    public String getId() {
        return id;
    }


    public String getOwner() {
        return owner;
    }


    public int getProjectileDamage() {
        return projectileDamage;
    }


    public int getXcoord() {
        return xcoord;
    }


    public int getYcoord() {
        return ycoord;
    }


    public float getSpeed() {
        return speed;
    }


    public float getAngle() {
        return angle;
    }
}
