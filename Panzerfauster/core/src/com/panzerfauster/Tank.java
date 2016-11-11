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
        super(image_path, isEnemy, name, xcoordinate, ycoordinate, speed, angle);

    }


    public void fire() {
        //        Get power of this tank
        //        Get projectile speed of this tank
        //        Start at (this.xcoord, this.ycoord)
        //        Set target at (range * arccos(this.angle), range * arcsin(this.angle));

        GameState.addProjectile(
            new Projectile("sprites/Projectile.png", false, null, this.xcoord, this.ycoord, 15, this.angle, this));
    }
}
