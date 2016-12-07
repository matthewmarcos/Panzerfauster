package com.panzerfauster;

import java.util.ArrayList;

/**
 * Created by matt on 11/10/16.
 */
public class Tank extends Entity {

    private static ArrayList<Tank> tanks = new ArrayList<Tank>();
    private float cooldown, fireSpeed;
    private double lastFired;
    private int    bulletDamage, kills;
    private boolean fired;


    public boolean isFired() {
        return fired;
    }


    public void setFired(boolean fired) {
        this.fired = fired;
    }


    public Tank(String image_path, boolean isEnemy, String name, int xcoordinate, int ycoordinate, int speed,
                float cooldown, float angle) {

        super(image_path, "TANK", isEnemy, name, xcoordinate, ycoordinate, speed, angle);
        this.sprite.setSize(128, 128);
        this.sprite.setOriginCenter();
        this.setPosition(xcoordinate, ycoordinate);
        this.sprite.setRotation(this.angle);

        // Setup things about Tank
        this.cooldown = cooldown;
        this.lastFired = 0d;
        this.hp = 100;
        this.maxHp = 100;
        this.bulletDamage = 25;

        tanks.add(this);
        this.id = "tank-" + GameState.getState().getUsername();

    }


    public float getCooldown() {
        return cooldown;
    }


    public float getFireSpeed() {
        return fireSpeed;
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

        this.fired = true;
        GameState.getState().addProjectile(
            new Projectile("sprites/bomb3.png", false, null, this.xcoord + DeltaX, this.ycoord + DeltaY, 15, 100,
                this.angle, this));

        GameState.getState().updateServer();
    }


    private void move(float x, float y) {

        //Check if xcoord and ycoord are within map bounds
        if(this.xcoord + x < -1 * GameScreen.getScreen().getMapWidth() / 2) {
            return;
        }
        if(this.xcoord + x > GameScreen.getScreen().getMapWidth() / 2) {
            return;
        }
        if(this.ycoord + y < -1 * GameScreen.getScreen().getMapHeight() / 2) {
            return;
        }
        if(this.ycoord + y > GameScreen.getScreen().getMapHeight() / 2) {
            return;
        }

        this.xcoord += x;
        this.ycoord += y;

        this.setPosition(this.xcoord, this.ycoord);

        GameState.getState().updateServer();
    }


    public void lookAt(float x, float y) {
        //    Rotates the sprite of this entity to look at the mouse
        float mouseX, mouseY, realX, realY;
        mouseX = x - this.width / 2;
        realX = 0;
        mouseY = -y + this.height / 2;
        realY = 0;

        this.angle = (float)Math.toDegrees(Math.atan2(mouseY - realY, mouseX - realX));
        this.sprite.setRotation(this.angle);
    }

    public void setAngle(float angle) {
        this.angle = angle;
        this.sprite.setRotation(this.angle);
    }


    public void moveLeft() {
        //        Move left by speed
        this.move(-this.speed, 0);
    }


    public void moveRight() {
        //        Move right by speed
        this.move(this.speed, 0);
    }


    public void moveUp() {
        //        move up by speed
        this.move(0, this.speed);
    }


    public void moveDown() {
        //        move down by speed
        this.move(0, -this.speed);
    }


    public TankData getTankData() {
        return new TankData(this.id, this.hp, this.maxHp, this.bulletDamage, this.xcoord, this.ycoord, this.kills,
            this.lastFired, this.speed, this.angle, this.cooldown);
    }

}
