package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by matt on 12/7/16.
 */
public class MechanicsScreen implements Screen {

    private String message = "asdasdasdasdasdasd";
    private Stage      stage;
    private BitmapFont font;
    private Table      messageTable;
    private Texture    controlsTexture;
    private Sprite     controlsSprite;
    private static MechanicsScreen screen = new MechanicsScreen();
    private SpriteBatch batch;


    private MechanicsScreen() {

    }


    public static MechanicsScreen getScreen() {
        return screen;
    }


    @Override
    public void show() {
        stage = new Stage();
        font = new BitmapFont();
        batch = new SpriteBatch();
        controlsTexture = new Texture(Gdx.files.internal("mechanics.png"));
        controlsSprite = new Sprite(controlsTexture);

        messageTable = new Table();

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                // Return to the MenuScreen
                if(event.getKeyCode() == Input.Keys.ESCAPE) {
                    Panzerfauster.getInstance().setMainMenuScreen();
                }
                return true;
            }
        });

        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        controlsSprite.draw(batch);
        batch.end();
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
