package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by matt on 11/23/16.
 */
public class MenuScreen implements Screen {

    private static MenuScreen screen = new MenuScreen();
    private Stage      stage;
    private Table      buttonTable;
    private TextButton playButton, enterButton, startButton;
    private TextButtonStyle textButtonStyle;
    private BitmapFont      font;
    private Skin            skin;
    private TextureAtlas    atlas;


    private MenuScreen() {

    }


    public static MenuScreen getScreen() {
        return screen;
    }


    @Override
    public void show() {
        stage = new Stage();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        skin = new Skin();
        buttonTable = new Table();
        atlas = new TextureAtlas(Gdx.files.internal("icons/buttons/buttons.pack.atlas"));
        skin.addRegions(atlas);

        stage.addActor(buttonTable);

        //Initialize the button skins
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("unpressed");
        textButtonStyle.down = skin.getDrawable("pressed");
        textButtonStyle.checked = skin.getDrawable("unpressed");

        initPlayButton();
        initEnterButton();

        buttonTable.setWidth(256f);

        buttonTable.add(enterButton).size(256, 54).padBottom(30f).row();
        buttonTable.add(playButton).size(256, 54).padBottom(30f).row();

        buttonTable.setPosition(435, 300);

        Gdx.input.setInputProcessor(stage);
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
        stage.dispose();
    }


    private void initPlayButton() {

        playButton = new TextButton("Play", textButtonStyle);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent ev, float x, float y) {
                Panzerfauster.getInstance().setGameScreen();
            }
        });

    }


    private void initEnterButton() {

        enterButton = new TextButton("Enter", textButtonStyle);

        enterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent ev, float x, float y) {
                System.out.println("Clicked Enter Button");
            }
        });

    }

}
