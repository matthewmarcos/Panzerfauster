package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * Created by matt on 11/10/16.
 * Entities are objects that move along the screen. They may be bullets,
 * tanks, or enemies, or enemy bullets, etc.
 */
public class Entity extends BodyDef {

    protected Texture texture;
    protected Sprite  sprite;
    protected int     xcoord, ycoord, width, height;
    protected float speed, angle;
    private String type;

    protected boolean isAlive;


    protected Entity(String image_path, String type, boolean isEnemy, String name, int xcoordinate, int ycoordinate,
                     int speed, float angle) {

        super();

        try {
            this.texture = new Texture(Gdx.files.internal(image_path));
            this.sprite = new Sprite(texture);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        // Refers to the game screen
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        this.speed = speed;
        this.angle = angle;

        this.type = type;
    }


    public void setPosition(int x, int y) {
        //        Sets the position of the object to (x, y) and the sprite to match
        this.xcoord = x;
        this.ycoord = y;

        int printX = (int)(this.xcoord - this.sprite.getHeight() / 2);
        int printY = (int)(this.ycoord - this.sprite.getHeight() / 2);

        this.sprite.setPosition(printX, printY);
    }


    public void lookAt(float x, float y) {
        //    Rotates the sprite of this entity to look at the mouse
        float mousex, mousey, realx, realy;
        mousex = x - this.width / 2;
        //        realx = this.xcoord - this.width/2;
        realx = 0;
        mousey = -y + this.height / 2;
        //        realy = this.ycoord - this.height/2;
        realy = 0;

        this.angle = (float)Math.toDegrees(Math.atan2(mousey - realy, mousex - realx));
        this.sprite.setRotation(this.angle);
    }


    public void moveLeft() {
        //        Move left by speed
        this.move(-this.speed, 0);
    }


    public void moveRight() {
        //        Move right by speed
        this.move(this.speed, 0);
    }


    public void moveUp() {
        //        move up by speed
        this.move(0, this.speed);
    }


    public void moveDown() {
        //        move down by speed
        this.move(0, -this.speed);
    }


    public void printLocation() {
        System.out.println(
            "Tank: X: " + xcoord + " Y: " + ycoord + " Sprite: X: " + this.sprite.getX() + " Y: " + this.sprite.getY());
    }


    public Sprite getSprite() {
        //  Returns the image that represents this entity
        return this.sprite;
    }


    public int getX() {

        return this.xcoord;
    }


    public int getY() {

        return this.ycoord;
    }


    public Texture getTexture() {

        return this.texture;
    }


    private void move(float x, float y) {

        //Check if xcoord and ycoord are within map bounds
        if(this.xcoord + x < -1 * Panzerfauster.getMapWidth() / 2) {
            return;
        }
        if(this.xcoord + x > Panzerfauster.getMapWidth() / 2) {
            return;
        }
        if(this.ycoord + y < -1 * Panzerfauster.getMapHeight() / 2) {
            return;
        }
        if(this.ycoord + y > Panzerfauster.getMapHeight() / 2) {
            return;
        }

        this.xcoord += x;
        this.ycoord += y;

        this.setPosition(this.xcoord, this.ycoord);
    }

    public void updateStatus(EntityPacket p) {
        this.xcoord = p.getX();
        this.ycoord = p.getY();
        this.speed = p.getSpeed();
        this.angle = p.getAngle();
    }

    public EntityPacket getPacket() {
        return new EntityPacket(this.type, this.xcoord, this.ycoord, this.speed, this.angle);
    }

    public boolean isAlive() {
        return this.isAlive;
    }
}
