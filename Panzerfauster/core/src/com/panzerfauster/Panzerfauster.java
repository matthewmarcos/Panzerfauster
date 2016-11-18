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
import com.badlogic.gdx.physics.box2d.*;

public class Panzerfauster extends ApplicationAdapter implements ApplicationListener, InputProcessor {

    private SpriteBatch batch;
    private BitmapFont  font;
    private Texture     mapTexture;
    private static Sprite      mapSprite;
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
        player = new Tank("sprites/tank1.png", false, "Player", 0, 0, 5, 0);

        GameState.addTank(player);

        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void render() {

        // Have to poll keyboard for input so it will get input continuously
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp();
        }

        // Make player look at the mouse
        player.lookAt(Gdx.input.getX(), Gdx.input.getY());

        //Move and update camera to location of player Tank
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        //  Start rendering
        batch.begin();
        mapSprite.draw(batch); // draw the map
        for(Projectile s : GameState.getProjectiles()) {
            s.getSprite().draw(batch);
        }
        for(Tank s : GameState.getTanks()) {
            s.getSprite().draw(batch);
        }
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


    public static int getMapWidth() {
        return (int)mapSprite.getWidth();
    }


    public static int getMapHeight() {
        return (int)mapSprite.getHeight();
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
        // This function fires when the user clicks on the screen.
        // The player fires a projectule in the direction it is facing
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
