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
import com.badlogic.gdx.physics.box2d.*;

public class Panzerfauster extends Game {

    protected SpriteBatch batch;

    private GameScreen gameScreen;
    private MenuScreen menuScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameScreen = GameScreen.getScreen();
        menuScreen = MenuScreen.getScreen();
        // this.setScreen(menuScreen);
        this.setScreen(gameScreen);
    }


    @Override
    public void render() {
        super.render();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }



}
