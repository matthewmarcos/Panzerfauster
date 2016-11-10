package com.panzerfauster;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	private Sprite player;
	private float xPos, yPos, speed;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture temp = new Texture(Gdx.files.internal("sprites/Tank.png"));
        player = new Sprite(temp);
        font = new BitmapFont();
        font.setColor(Color.RED);

        this.winWidth = Gdx.graphics.getWidth();
        this.winHeight = Gdx.graphics.getHeight();
        this.speed = 10;

        xPos = winWidth/2 - temp.getWidth()/2;
        yPos = winHeight/2 - temp.getWidth()/2;
        player.setPosition(xPos, yPos);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 1, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.setPosition(xPos, yPos);

        batch.begin();
        //batch.draw(playerSprite, x, y);
        player.draw(batch);
        batch.end();
    }
	
	@Override
	public void dispose () {
		batch.dispose();
        font.dispose();
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
        System.out.println("character");
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

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
