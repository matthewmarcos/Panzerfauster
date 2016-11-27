package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

/**
 * Created by matt on 11/23/16.
 */
public class MenuScreen implements Screen {

    private static MenuScreen screen = new MenuScreen();
    private Stage      stage;
    private Table      connectTable;
    private TextButton playButton, enterButton;
    private TextButtonStyle textButtonStyle;
    private TextField       usernameTextField, ipTextField;
    private TextField.TextFieldStyle textFieldStyle;
    private BitmapFont               font;
    private Skin                     buttonSkin, textFieldSkin;
    private TextureAtlas buttonAtlas, textFieldAtlas;


    private MenuScreen() {

    }


    public static MenuScreen getScreen() {
        return screen;
    }


    @Override
    public void show() {
        stage = new Stage();
        font = new BitmapFont();

        buttonSkin = new Skin();
        textFieldSkin = new Skin();

        connectTable = new Table();

        buttonAtlas = new TextureAtlas(Gdx.files.internal("icons/buttons/buttons.pack.atlas"));
        buttonSkin.addRegions(buttonAtlas);

        textFieldAtlas = new TextureAtlas(Gdx.files.internal("icons/textfield/textfield.atlas"));
        textFieldSkin.addRegions(textFieldAtlas);

        stage.addActor(connectTable);

        initTextButtonStyle();
        initTextFieldStyle();

        initPlayButton();
        initEnterButton();

        initUsernameTextField();
        initIpTextField();

        connectTable.setWidth(256f);
        connectTable.add(ipTextField).padBottom(10f).size(256f, 30f).row();
        connectTable.add(usernameTextField).padBottom(10f).size(256f, 30f).row();
        connectTable.add(enterButton).size(120, 30f).padBottom(30f).row();
        connectTable.add(playButton).size(120, 30f).padBottom(30f);

        // Place table at an arbitrary position
        // connectTable.setPosition(500 - 272, 300);

        connectTable.setPosition(10f, 400f);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 1, 0.4f, 0.05f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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


    private void initTextFieldStyle() {
        Drawable cursor = textFieldSkin.newDrawable("textfield", Color.BLACK);
        cursor.setMinWidth(2f);
        textFieldStyle = new TextFieldStyle(font, Color.WHITE, cursor,
            textFieldSkin.newDrawable("textfield", Color.TEAL), textFieldSkin.getDrawable("textfield"));
        textFieldStyle.fontColor = Color.BLACK;
    }


    private void initIpTextField() {
        //Initialize the IP Address TextField
        ipTextField = new TextField("127.0.0.1", textFieldStyle);
        ipTextField.setAlignment(Align.center);
    }


    private void initUsernameTextField() {
        usernameTextField = new TextField("Noob Pwnzer 69", textFieldStyle);
        usernameTextField.setAlignment(Align.center);
    }


    private void initTextButtonStyle() {
        //Initialize the button skins
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.BLACK;
        textButtonStyle.up = buttonSkin.getDrawable("unpressed");
        textButtonStyle.down = buttonSkin.getDrawable("pressed");
        textButtonStyle.checked = buttonSkin.getDrawable("unpressed");
    }


    private void initPlayButton() {

        playButton = new TextButton("Play", textButtonStyle);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent ev, float x, float y) {
                playButton.setDisabled(true);

                Panzerfauster.getInstance().setGameScreen();
            }
        });

    }


    private void initEnterButton() {

        enterButton = new TextButton("Enter", textButtonStyle);

        enterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent ev, float x, float y) {
                String ipAddress = ipTextField.getText();
                String username = usernameTextField.getText();
                ipTextField.setDisabled(true);
                usernameTextField.setDisabled(true);
                enterButton.setDisabled(true);
                System.out.println("Hello " + username + "! You are trying to connect to: " + ipAddress);
            }
        });

    }

}
