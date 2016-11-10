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

public class Panzerfauster extends ApplicationAdapter implements InputProcessor {
    private int winWidth, winHeight;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture temp;
    private Sprite player;
    private float xPos, yPos, speed, angle;

    @Override
    public void create () {
        batch = new SpriteBatch();
        temp = new Texture(Gdx.files.internal("sprites/Tank.png"));
        player = new Sprite(temp);
        font = new BitmapFont();
        font.setColor(Color.RED);

        this.winWidth = Gdx.graphics.getWidth();
        this.winHeight = Gdx.graphics.getHeight();
        this.speed = 5;
        this.angle = 0;

        xPos = winWidth/2 - temp.getWidth()/2;
        yPos = winHeight/2 - temp.getWidth()/2;

        player.setPosition(xPos, yPos);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render () {


//      Have to poll keyboard for input so it will get input continuously
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            xPos -= this.speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            xPos += this.speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            yPos -= this.speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            yPos += this.speed;
        }

        Gdx.gl.glClearColor(0.1f, 1, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.setPosition(xPos, yPos);

        batch.begin();
        player.draw(batch);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
        temp.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int keycode) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
