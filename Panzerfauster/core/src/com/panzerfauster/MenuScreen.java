package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * Created by matt on 11/23/16.
 */
public class MenuScreen implements Screen {

    private Stage      stage;
    private Table      buttonTable;
    private TextButton playButton, enterButton, startButton;
    private TextButtonStyle playTextButtonStyle, enterTextButtonStyle, startTextButtonStyle;
    private BitmapFont   font;
    private Skin         skin;
    private TextureAtlas atlas;

    private static MenuScreen screen = new MenuScreen();


    private MenuScreen() {

    }


    @Override
    public void show() {
        stage = new Stage();
        font = new BitmapFont();
        skin = new Skin();
        buttonTable = new Table();
        atlas = new TextureAtlas(Gdx.files.internal("icons/buttons/buttons.pack.atlas"));
        skin.addRegions(atlas);

        stage.addActor(buttonTable);
        initPlayButton();
        initEnterButton();

        buttonTable.setWidth(100f);
        // buttonTable.align(Align.center | Align.top);

        buttonTable.add(enterButton).padBottom(30f);
        buttonTable.row();
        buttonTable.add(playButton).padBottom(30f);

        buttonTable.setPosition(435, 300);

        Gdx.input.setInputProcessor(stage);
    }


    private void initEnterButton() {
        enterTextButtonStyle = new TextButtonStyle();
        enterTextButtonStyle.font = font;
        enterTextButtonStyle.up = skin.getDrawable("enter");
        enterTextButtonStyle.down = skin.getDrawable("enter");
        enterTextButtonStyle.checked = skin.getDrawable("enter");
        enterButton = new TextButton("", enterTextButtonStyle);
    }


    private void initStartButton() {
        startTextButtonStyle = new TextButtonStyle();
        startTextButtonStyle.font = font;
        startTextButtonStyle.up = skin.getDrawable("start");
        startTextButtonStyle.down = skin.getDrawable("start");
        startTextButtonStyle.checked = skin.getDrawable("start");
        startButton = new TextButton("", startTextButtonStyle);

        startButton.addListener(new ClickListener() {
            public void clicked(InputEvent ev, float x, float y) {
                System.out.println("Whas");
                Panzerfauster.getInstance().setGameScreen();
            }
        });
    }


    private void initPlayButton() {
        playTextButtonStyle = new TextButtonStyle();
        playTextButtonStyle.font = font;
        playTextButtonStyle.up = skin.getDrawable("play");
        playTextButtonStyle.down = skin.getDrawable("play");
        playTextButtonStyle.checked = skin.getDrawable("play");
        playButton = new TextButton("", playTextButtonStyle);


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
