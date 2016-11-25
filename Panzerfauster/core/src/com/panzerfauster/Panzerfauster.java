package com.panzerfauster;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Panzerfauster extends Game {

    private SpriteBatch batch;
    private GameScreen  gameScreen = GameScreen.getScreen();
    private MenuScreen  menuScreen = MenuScreen.getScreen();
    private static Panzerfauster instance;


    @Override
    public void create() {
        // Initially set this to menuScreen
        instance = this;
        batch = new SpriteBatch();
        this.setScreen(menuScreen);
    }

    public void setGameScreen() {
        this.setScreen(gameScreen);
    }


    public static Panzerfauster getInstance() {
        return instance;
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
