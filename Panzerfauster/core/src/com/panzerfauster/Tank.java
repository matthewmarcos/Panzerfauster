package com.panzerfauster;

/**
 * Created by matt on 11/10/16.
 */
public class Tank extends Entity {

    public Tank(
        String image_path, // Location of image
        boolean isEnemy, // Will it be hostile?
        String name,
        int xcoordinate, //xLocation on screen
        int ycoordinate,  //yLocation on screen
        float angle
    ) {
        super(image_path, isEnemy, name, xcoordinate, ycoordinate);

    }
}
