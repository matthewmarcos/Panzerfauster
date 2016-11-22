package com.panzerfauster;

/**
 * Created by matt on 11/10/16.
 */
public class Tank extends Entity {

    public Tank(String image_path, // Location of image
                boolean isEnemy, // Will it be hostile?
                String name, int xcoordinate, //xLocation on screen
                int ycoordinate,  //yLocation on screen
                int speed, float angle) {
        super(image_path, "TANK", isEnemy, name, xcoordinate, ycoordinate, speed, angle);
        this.sprite.setSize(128, 128);
        this.sprite.setOriginCenter();
        this.setPosition(xcoordinate, ycoordinate);
        this.sprite.setRotation(this.angle);
    }


    public void fire() {
        //        Get power of this tank
        //        Get projectile speed of this tank
        //        Start at (this.xcoord, this.ycoord)
        //        Set target at (range * arccos(this.angle), range * arcsin(this.angle));

        int DeltaX, DeltaY;

        DeltaX = (int)(this.sprite.getHeight()/2 * Math.cos(Math.toRadians(this.angle)));
        DeltaY = (int)(this.sprite.getHeight()/2 * Math.sin(Math.toRadians(this.angle)));

        GameState.addProjectile(
            new Projectile("sprites/bomb3.png", false, null, this.xcoord + DeltaX, this.ycoord + DeltaY, 30, this.angle, this));
    }
}
