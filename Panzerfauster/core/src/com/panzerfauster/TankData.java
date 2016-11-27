package com.panzerfauster;

/**
 * Created by matt on 11/22/16.
 */

public class TankData {

    private String id;
    private int    hp, maxHp, projectileDamage, xcoord, ycoord, kills;
    private double lastFired;
    private float speed, angle, cooldown;


    public TankData(String id, int hp, int maxHp, int projectileDamage, int xcoord, int ycoord, int kills,
                    double lastFired, float speed, float angle, float cooldown) {
        this.id = id;
        this.hp = hp;
        this.maxHp = maxHp;
        this.projectileDamage = projectileDamage;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.kills = kills;
        this.lastFired = lastFired;
        this.speed = speed;
        this.angle = angle;
        this.cooldown = cooldown;
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


    public int getKills() {
        return kills;
    }


    public double getLastFired() {
        return lastFired;
    }


    public float getSpeed() {
        return speed;
    }


    public float getAngle() {
        return angle;
    }


    public float getCooldown() {
        return cooldown;
    }
}
