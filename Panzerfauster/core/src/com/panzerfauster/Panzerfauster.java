package com.panzerfauster;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Panzerfauster extends ApplicationAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture temp;
    private Tank player;
    private float xPos, yPos;
    private OrthographicCamera camera;

    @Override
    public void create () {
        batch = new SpriteBatch();
        player = new Tank("sprites/Tank.png", false, "Tank", 500, 300, 5, 0);
        camera = new OrthographicCamera();
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

        player.lookAt(Gdx.input.getX(), Gdx.input.getY());

        Gdx.gl.glClearColor(0.1f, 1, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        player.getSprite().draw(batch);
        batch.end();
    }

    @Override
    public void dispose () {
//        player.getTexture().dispose();
        batch.dispose();
    }
}
