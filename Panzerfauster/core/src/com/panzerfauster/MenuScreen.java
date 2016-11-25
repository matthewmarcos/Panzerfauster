package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Created by matt on 11/23/16.
 */
public class MenuScreen implements Screen {

    private Stage           stage;
    private TextButton      playButton;
    private TextButtonStyle playTextButtonStyle;
    private BitmapFont      font;
    private Skin            skin;
    private TextureAtlas    atlas;

    private static MenuScreen screen = new MenuScreen();


    private MenuScreen() {

    }


    @Override
    public void show() {
        stage = new Stage();
        font = new BitmapFont();
        skin = new Skin();
        atlas = new TextureAtlas(Gdx.files.internal("icons/buttons/buttons.pack.atlas"));
        skin.addRegions(atlas);

        setPlayButton();
        Gdx.input.setInputProcessor(stage);
    }


    private void setPlayButton() {
        playTextButtonStyle = new TextButtonStyle();
        playTextButtonStyle.font = font;
        playTextButtonStyle.up = skin.getDrawable("play");
        playTextButtonStyle.down = skin.getDrawable("play");
        playTextButtonStyle.checked = skin.getDrawable("play");
        playButton = new TextButton("", playTextButtonStyle);
        stage.addActor(playButton);
    }


    public static MenuScreen getScreen() {
        return screen;
    }


    @Override
    public void render(float delta) {
        stage.draw();
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
