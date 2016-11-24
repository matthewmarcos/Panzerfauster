package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Created by matt on 11/23/16.
 */
public class MenuScreen implements Screen {

    private static MenuScreen screen = new MenuScreen();
    private TextButton      tempButton;
    private TextButtonStyle textButtonStyle;
    private TextureAtlas    atlas;


    private MenuScreen() {
        try {
            // atlas = new TextureAtlas(Gdx.files.internal("icons/play.jpg"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static MenuScreen getScreen() {
        return screen;
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);
    }


    @Override
    public void render(float delta) {

    }


    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void pause() {

    }


    @Override
    public void resume() {

    }


    @Override
    public void hide() {

    }


    @Override
    public void dispose() {

    }
}
