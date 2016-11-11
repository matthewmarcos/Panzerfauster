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

public class Panzerfauster extends ApplicationAdapter implements ApplicationListener, InputProcessor {

    private SpriteBatch batch;
    private BitmapFont  font;
    private Texture     mapTexture;
    private Sprite      mapSprite;
    private Texture     temp;
    private Tank        player;
    private float       xPos, yPos;
    private OrthographicCamera camera;
    private GameState          gamestate;


    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(1000, 600);
        mapTexture = new Texture(Gdx.files.internal("tiles/map.jpg"));
        mapSprite = new Sprite(mapTexture);
        mapSprite.setOrigin(0f, 0f);
        mapSprite.setPosition(-mapSprite.getWidth() / 2, -mapSprite.getHeight() / 2);
        player = new Tank("sprites/Tank.png", false, "Tank", 0, 0, 5, 0);

        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void render() {

        // Have to poll keyboard for input so it will get input continuously
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft();
            camera.translate(-5, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight();
            camera.translate(5, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown();
            camera.translate(0, -5);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp();
            camera.translate(0, 5);
        }

        player.lookAt(Gdx.input.getX(), Gdx.input.getY());
        camera.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        //  Start rendering
        batch.begin();
        mapSprite.draw(batch);
        for(Tank s : GameState.getTanks()) {
            s.getSprite().draw(batch);
        }

        for(Projectile s : GameState.getProjectiles()) {
            s.getSprite().draw(batch);
        }
        player.getSprite().draw(batch);
        batch.end();
    }


    @Override
    public void dispose() {
        player.getTexture().dispose();
        batch.dispose();
        mapTexture.dispose();
        for(Tank s : GameState.getTanks()) {
            s.getTexture().dispose();
        }
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Fire!");
        player.fire();
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
