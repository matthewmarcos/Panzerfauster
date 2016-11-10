package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by matt on 11/10/16.
 * Entities are objects that move along the screen. They may be bullets,
 * tanks, or enemies, or enemy bullets, etc.
 */
public class Entity {

    protected Texture image;
    protected int xcoord, ycoord;

    protected Entity(
            String image_path, // Location of image
            boolean isEnemy, // Will it be hostile?
            String name,
            int xcoordinate, //xLocation on screen
            int ycoordinate //yLocation on screen
    ) {
        try {
            this.image = new Texture(Gdx.files.internal(image_path));
            this.xcoord = xcoordinate;
            this.ycoord = ycoordinate;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getX() {
//  Returns the x coordinate to draw the image
        return xcoord - image.getHeight() / 2;
    }

    public int getY() {
//  Returns the y coordinate to draw the image
        return ycoord - image.getHeight() / 2;
    }

    public int getXpos() {
        return this.xcoord;
    }

    public int getYpos() {
        return this.ycoord;
    }

    public Texture getImage() {
//  Returns the image that represents this entity
        return this.image;
    }
}
