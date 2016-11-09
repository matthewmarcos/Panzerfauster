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

    public Entity(
        String image_path, // Location of image
        boolean isEnemy, // Will it be hostile?
        String name,
        int xcoordinate, //xLocation on screen
        int ycoordinate  //yLocation on screen
    ) {
        try {
            this.image = new Texture(Gdx.files.internal(image_path));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
