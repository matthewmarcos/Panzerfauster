package com.panzerfauster;

/**
 * Created by matt on 11/11/16.
 */
public class Projectile extends Entity{
    public Projectile(
        String image_path, // Location of image
        boolean isEnemy, // Will it be hostile?
        String name,
        int xcoordinate, //xLocation on screen
        int ycoordinate,  //yLocation on screen
        int speed,
        float angle
    ) {
        super(image_path, isEnemy, name, xcoordinate, ycoordinate, speed, angle);
    }

    public void update() {
//        Updates the bullet location and sprite
        int deltaX = (int)Math.acos(this.angle);
        int deltaY = (int)Math.asin(this.angle);

        if (angle < 0) {
            deltaX *= -1;
        }

        if (angle > 90 || angle < -90) {
            deltaY *= -1;
        }

        this.setPosition(this.xcoord + deltaX, this.ycoord + deltaY);
    }
}
