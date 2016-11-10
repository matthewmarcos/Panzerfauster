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
        this.xcoord = x;
        this.ycoord = y;

        int printX = (int)(this.xcoord - this.sprite.getHeight()/2);
        int printY = (int)(this.ycoord - this.sprite.getHeight()/2);
//        this.angle = -(float) Math.toDegrees(Math.atan2(Gdx.input.getY() - this.ycoord, Gdx.input.getX() - this.xcoord));
//        this.lookAt(Gdx.input.getX(), Gdx.input.getY());

        this.sprite.setPosition(printX, printY);
    }

    public void lookAt(float x, float y) {
//    Rotates the sprite of this entity to look at
        float mousex, mousey, realx, realy;
        mousex = x - this.width/2;
        realx = this.xcoord - this.width/2;
        mousey = -y + this.height/2;
        realy = this.ycoord - this.height/2;

        this.angle = (float)Math.toDegrees(Math.atan2(mousey - realy, mousex - realx));
        this.sprite.setRotation(this.angle);
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
