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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by matt on 11/23/16.
 */
public class MenuScreen implements Screen {

    private static MenuScreen screen = new MenuScreen();
    private Stage      stage;
    private Table      buttonTable;
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

        buttonTable = new Table();

        buttonAtlas = new TextureAtlas(Gdx.files.internal("icons/buttons/buttons.pack.atlas"));
        buttonSkin.addRegions(buttonAtlas);

        textFieldAtlas = new TextureAtlas(Gdx.files.internal("icons/textfield/textfield.atlas"));
        textFieldSkin.addRegions(textFieldAtlas);

        stage.addActor(buttonTable);

        initTextButtonStyle();
        initTextFieldStyle();

        initPlayButton();
        initEnterButton();

        initUsernameTextField();
        initIpTextField();

        buttonTable.setWidth(512f);
        buttonTable.add(ipTextField).padBottom(10f).row();
        buttonTable.add(usernameTextField).padBottom(10f).row();
        buttonTable.add(enterButton).size(256, 54).padBottom(30f).row();
        buttonTable.add(playButton).size(256, 54).padBottom(30f).row();

        // Place table at an arbitrary position
        buttonTable.setPosition(500 - 272, 300);

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


    private void initTextFieldStyle() {
        textFieldStyle = new TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = textFieldSkin.getDrawable("textfield");
    }


    private void initIpTextField() {
        //Initialize the IP Address TextField
        ipTextField = new TextField("", textFieldStyle);
        ipTextField.setMessageText("test");
        ipTextField.setPosition(0, 0);

    }


    private void initUsernameTextField() {
        usernameTextField = new TextField("", textFieldStyle);
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
