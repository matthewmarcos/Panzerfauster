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

    protected int hp, xcoord, ycoord, width, height, id;
    protected float speed, angle;
    protected String  type;
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


    protected int getId() {
        return this.id;
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


    public int getHp() {
        return hp;
    }


    public int getXcoord() {
        return xcoord;
    }


    public int getYcoord() {
        return ycoord;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    public float getSpeed() {
        return speed;
    }


    public float getAngle() {
        return angle;
    }


    public String getType() {
        return type;
    }


    public boolean isAlive() {
        return this.isAlive;
    }

}
