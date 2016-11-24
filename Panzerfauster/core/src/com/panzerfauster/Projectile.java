package com.panzerfauster;

/**
 * Created by matt on 11/11/16.
 */
public class Projectile extends Entity {

    protected Tank owner;

    protected int lifeRemaining = 100;


    public Projectile(String image_path, boolean isEnemy, String name, int xcoordinate, int ycoordinate, int speed,
                      int lifespan, float angle, Tank owner) {
        super(image_path, EntityData.PROJECTILE, isEnemy, name, xcoordinate, ycoordinate, speed, angle);
        this.isAlive = true;
        this.owner = owner;

        // Shrink the size of the projectile
        this.sprite.setSize(64, 64);
        this.sprite.setScale(0.2f);
        this.sprite.setOriginCenter();
        this.sprite.setRotation(this.angle);
        this.setPosition(xcoordinate, ycoordinate);

        //Set bullet statistics
        this.lifeRemaining = lifespan;
    }


    public void update() {
        // Updates the bullet location and sprite orientation
        int deltaX = (int)(Math.cos(Math.toRadians(this.angle)) * this.speed);
        int deltaY = (int)(Math.sin(Math.toRadians(this.angle)) * this.speed);

        if(--lifeRemaining < 0) {
            this.isAlive = false;
            return;
        }

        this.setPosition(this.xcoord + deltaX, this.ycoord + deltaY);
    }


    public void printMe() {
        System.out.println("xCoord: " + this.xcoord + " yCoord: " + this.ycoord + " angle: " + this.angle);
    }
}
