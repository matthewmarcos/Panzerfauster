package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by matt on 11/10/16.
 * Entities are objects that move along the screen. They may be bullets,
 * tanks, or enemies, or enemy bullets, etc.
 */
public class Entity {

    protected Sprite sprite;
    protected int xcoord, ycoord, width, height;
    protected float speed;
    protected Texture texture;

    protected Entity(
            String image_path, // Location of image
            boolean isEnemy, // Will it be hostile?
            String name,
            int xcoordinate, //xLocation on screen
            int ycoordinate, //yLocation on screen
            int speed,
            float angle
    ) {
        try {
            this.texture = new Texture(Gdx.files.internal(image_path));
            this.sprite = new Sprite(texture);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        this.setPosition(xcoordinate, ycoordinate);
        this.speed = speed;

        System.out.println("Created: " + name);
    }

    public void setPosition(int x, int y) {
        this.xcoord = x;
        this.ycoord = y;

        int printX = (int)(this.xcoord - this.sprite.getHeight()/2);
        int printY = (int)(this.ycoord - this.sprite.getHeight()/2);

        this.sprite.setPosition(printX, printY);
    }

    public void moveLeft() {
        this.move(
            -this.speed,
            0
        );
    }

    public void moveRight() {
        this.move(
            this.speed,
            0
        );
    }

    public void moveUp() {
        this.move(
            0,
            this.speed
        );
    }

    public void moveDown() {
        this.move(
            0,
            -this.speed
        );
    }

    public Sprite getSprite() {
//  Returns the image that represents this entity
        return this.sprite;
    }

    public Texture getTexture() {
        return this.texture;
    }

    private void move(float x, float y) {
        this.xcoord += x;
        this.ycoord += y;

        this.setPosition(this.xcoord, this.ycoord);
    }

}
