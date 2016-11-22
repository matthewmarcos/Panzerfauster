package com.panzerfauster;

/**
 * Created by matt on 11/11/16.
 */
public class Projectile extends Entity {

    protected Tank    owner;
    protected boolean isAlive;
    protected int life = 100;


    public Projectile(String image_path, boolean isEnemy, String name, int xcoordinate, int ycoordinate, int speed,
                      float angle, Tank owner) {
        super(image_path, "PROJECTILE", isEnemy, name, xcoordinate, ycoordinate, speed, angle);
        this.isAlive = true;
        this.owner = owner;

        // Shrink the size of the projectile
        this.sprite.setSize(64, 64);
        this.sprite.setScale(0.2f);
        this.sprite.setOriginCenter();
        this.sprite.setRotation(this.angle);
        this.setPosition(xcoordinate, ycoordinate);
    }


    public void update() {
        // Updates the bullet location and sprite
        int deltaX = (int)(Math.cos(Math.toRadians(this.angle)) * this.speed);
        int deltaY = (int)(Math.sin(Math.toRadians(this.angle)) * this.speed);

        if(--life < 0) {
            this.isAlive = false;
            return;
        }

        this.setPosition(this.xcoord + deltaX, this.ycoord + deltaY);
        // this.setPosition((int)(this.xcoord + this.speed), this.ycoord);
    }


    public boolean isAlive() {
        return this.isAlive;
    }


    public void printMe() {
        System.out.println("xCoord: " + this.xcoord + " yCoord: " + this.ycoord + " angle: " + this.angle);
    }
}
