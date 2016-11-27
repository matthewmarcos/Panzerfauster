package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

    private static Sprite mapSprite;
    private static GameScreen screen = new GameScreen();
    // private Screen gameScreen, menuScreen;
    private SpriteBatch batch;
    private BitmapFont  font;
    private Texture     mapTexture;
    private Texture     temp;
    private Tank        player;
    private float       xPos, yPos;
    private OrthographicCamera camera;
    private GameState          gamestate;


    private GameScreen() {
    }


    public static GameScreen getScreen() {

        return screen;
    }


    public static int getMapWidth() {

        return (int)mapSprite.getWidth();
    }


    public static int getMapHeight() {

        return (int)mapSprite.getHeight();
    }


    @Override
    public void show() {
        if(!GameState.getState().isGAME_RUNNING()) {
            batch = new SpriteBatch();
            camera = new OrthographicCamera(1000, 600);
            // mapTexture = new Texture(Gdx.files.internal("tiles/map.jpg"));
            mapTexture = new Texture(Gdx.files.internal("tiles/game_map.jpg"));
            mapSprite = new Sprite(mapTexture);
            mapSprite.setOrigin(0f, 0f);
            mapSprite.setPosition(-mapSprite.getWidth() / 2, -mapSprite.getHeight() / 2);
            player = new Tank("sprites/tank1.png", false, "Player", 0, 0, 5, 250f, 0);

            GameState.getState().setPlayer(player);
            GameState.addTank(player);
            GameState.getState().startGame();
            GameState.getState().setGAME_RUNNING(true);
        }

        // Use different listener when this becomes the state
        Gdx.input.setInputProcessor(GameState.getState());
    }


    @Override
    public void render(float delta) {

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
        if(Gdx.input.isTouched()) {
            player.fire();
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
        player.getTexture().dispose();
        batch.dispose();
        mapTexture.dispose();

        for(Tank s : GameState.getTanks()) {
            s.getTexture().dispose();
        }

        for(Projectile s : GameState.getProjectiles()) {
            s.getTexture().dispose();
        }
    }

}
