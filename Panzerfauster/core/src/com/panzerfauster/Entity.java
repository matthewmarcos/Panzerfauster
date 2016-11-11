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
    protected float speed, angle;
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

        this.sprite.setOriginCenter();
        this.width = 1000;
        this.height = 600;

        this.setPosition(xcoordinate, ycoordinate);
        this.speed = speed;

        System.out.println("Created: " + name);
    }

    public void setPosition(int x, int y) {
//        Sets the position of the object to (x, y) and the sprite to match
        this.xcoord = x;
        this.ycoord = y;

        int printX = (int)(this.xcoord - this.sprite.getHeight()/2);
        int printY = (int)(this.ycoord - this.sprite.getHeight()/2);

        this.sprite.setPosition(printX, printY);
    }

    public void lookAt(float x, float y) {
//    Rotates the sprite of this entity to look at the mouse
        float mousex, mousey, realx, realy;
        mousex = x - this.width/2;
//        realx = this.xcoord - this.width/2;
        realx = 0;
        mousey = -y + this.height/2;
//        realy = this.ycoord - this.height/2;
        realy = 0;
        
        this.angle = (float)Math.toDegrees(Math.atan2(mousey - realy, mousex - realx));
        this.sprite.setRotation(this.angle);
    }

    public void moveLeft() {
//        Move left by speed
        this.move(
            -this.speed,
            0
        );
    }

    public void moveRight() {
//        Move right by speed
        this.move(
            this.speed,
            0
        );
    }

    public void moveUp() {
//        move up by speed
        this.move(
            0,
            this.speed
        );
    }

    public void moveDown() {
//        move down by speed
        this.move(
            0,
            -this.speed
        );
    }

    public void printLocation() {
        System.out.print("Tank: X: " + xcoord + " Y: " + ycoord);
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
