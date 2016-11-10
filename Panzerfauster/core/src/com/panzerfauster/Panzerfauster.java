package com.panzerfauster;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Panzerfauster extends ApplicationAdapter {
    private int winWidth, winHeight;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture temp;
    private Tank player;
    private float xPos, yPos;

    @Override
    public void create () {
        batch = new SpriteBatch();
//        temp = new Texture(Gdx.files.internal("sprites/Tank.png"));
//        player = new Sprite(temp);
//        font = new BitmapFont();
//        font.setColor(Color.RED);
        this.winWidth = Gdx.graphics.getWidth();
        this.winHeight = Gdx.graphics.getHeight();

        player = new Tank("sprites/Tank.png", false, "Tank", 400, 300, 5, 0);

    }

    @Override
    public void render () {
//      Have to poll keyboard for input so it will get input continuously
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp();
        }

        Gdx.gl.glClearColor(0.1f, 1, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        player.getSprite().draw(batch);
        batch.end();
    }

    @Override
    public void dispose () {
        player.getTexture().dispose();
        batch.dispose();
    }
}
