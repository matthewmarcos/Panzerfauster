package com.panzerfauster;

/**
 * Created by matt on 11/22/16.
 */

public class EntityPacket {

    private int xcoord, ycoord;
    private int id, hp, damage;
    private float lastFired;
    private float speed, angle, cooldown;

    private String entityType;

    public static final String PROJECTILE = "PROJECTILE";
    public static final String TANK       = "TANK";
    public static final String ENEMY      = "ENEMY";


    public EntityPacket(String entityType, int xcoord, int ycoord, float speed, float angle) {
        //Check if all values exist.
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.speed = speed;
        this.angle = angle;
        this.entityType = entityType;

    }


    public String getType() {
        return this.entityType;
    }


    public int getX() {
        return this.xcoord;
    }


    public int getY() {
        return this.ycoord;
    }


    public float getSpeed() {
        return this.speed;
    }


    public float getAngle() {
        return this.angle;
    }

}
