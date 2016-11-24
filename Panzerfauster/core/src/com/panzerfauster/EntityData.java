package com.panzerfauster;

/**
 * Created by matt on 11/22/16.
 */

public class EntityData {

    public static final String PROJECTILE = "PROJECTILE";
    public static final String TANK       = "TANK";
    public static final String ENEMY      = "ENEMY";
    private String id;
    private int    hp, maxHp, projectileDamage, xcoord, ycoord;
    private float lastFired;
    private float speed, angle, cooldown;
    private String entityType;


    public EntityData(String entityType, String id, int hp, int maxHp, int projectileDamage, int xcoord, int ycoord,
                      float lastFired, float speed, float angle, float cooldown) {
        //Check if all values exist.
        this.entityType = entityType; // PROJECTILE, TANK, ENEMY
        this.id = id; // "Username" + entityType + "-" + number
        this.hp = hp; // 1 for bullets. 150 for tanks
        this.maxHp = maxHp;
        this.projectileDamage = projectileDamage;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.lastFired = lastFired;
        this.speed = speed; // Projectle speed and tank speed
        this.angle = angle; //Tanks - where it is looking
        this.cooldown = cooldown;
    }


    public String getType() {
        return this.entityType;
    }


    public String getId() {
        return id;
    }


    public int getHp() {
        return hp;
    }


    public int getMaxHp() {
        return maxHp;
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


    public float getLastFired() {
        return lastFired;
    }


    public float getCooldown() {
        return cooldown;
    }


    public float getSpeed() {
        return speed;
    }


    public float getAngle() {
        return angle;
    }


    public String getEntityType() {
        return entityType;
    }
}
