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
        // Set screen back to GameScreen
        this.setScreen(gameScreen);
    }

    public void setMainMenuScreen() {
        // Set screen back to MenuScreen
        this.setScreen(menuScreen);
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
