package com.panzerfauster;

import java.util.ArrayList;

/**
 * Created by matt on 11/10/16.
 */
public class Tank extends Entity {

    private static ArrayList<Tank> tanks = new ArrayList<Tank>();
    private float cooldown, fireSpeed;
    private double lastFired;


    public Tank(String image_path, boolean isEnemy, String name, int xcoordinate, int ycoordinate, int speed,
                float cooldown, float angle) {

        super(image_path, EntityPacket.TANK, isEnemy, name, xcoordinate, ycoordinate, speed, angle);
        this.sprite.setSize(128, 128);
        this.sprite.setOriginCenter();
        this.setPosition(xcoordinate, ycoordinate);
        this.sprite.setRotation(this.angle);

        // Setup things about Tank
        this.cooldown = cooldown;
        lastFired = 0;
        this.hp = 100;

        tanks.add(this);
        this.id = tanks.size();

    }


    public void fire() {
        //        Get power of this tank
        //        Get projectile speed of this tank
        //        Start at (this.xcoord, this.ycoord)
        //        Set target at (range * arccos(this.angle), range * arcsin(this.angle));

        // Check if tank can fire or is in cooldown
        double timeSinceLastFire = System.currentTimeMillis() - lastFired;
        if(timeSinceLastFire < cooldown) {
            return;
        }
        lastFired = System.currentTimeMillis();

        int DeltaX, DeltaY;

        DeltaX = (int)(this.sprite.getHeight() / 2 * Math.cos(Math.toRadians(this.angle)));
        DeltaY = (int)(this.sprite.getHeight() / 2 * Math.sin(Math.toRadians(this.angle)));

        GameState.addProjectile(
            new Projectile("sprites/bomb3.png", false, null, this.xcoord + DeltaX, this.ycoord + DeltaY, 30, 100,
                this.angle, this));
    }
}
