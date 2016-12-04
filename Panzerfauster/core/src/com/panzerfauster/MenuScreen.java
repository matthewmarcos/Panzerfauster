package com.panzerfauster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

<<<<<<< HEAD
import java.io.DataOutputStream;
=======
>>>>>>> aefb918cfd394b7f20dc1ca02b982d28d65556fc
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by matt on 11/23/16.
 */
public class MenuScreen implements Screen {

    private static MenuScreen screen = new MenuScreen();
    private Stage stage;
    private Table connectTable, chatTable;
    private TextButton playButton, enterButton;
    private TextButtonStyle textButtonStyle;
    private TextField       usernameTextField, ipTextField, chatBarTextField;
    private TextArea                 chatBoxTextArea;
    private TextField.TextFieldStyle textFieldStyle;
    private BitmapFont               font;
    private Skin                     buttonSkin, textFieldSkin;
    private TextureAtlas buttonAtlas, textFieldAtlas;
    private Socket           conn;
    private DataOutputStream out;
    private DataInputStream  in;


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
        chatTable = new Table();

        buttonAtlas = new TextureAtlas(Gdx.files.internal("icons/buttons/buttons.pack.atlas"));
        buttonSkin.addRegions(buttonAtlas);

        textFieldAtlas = new TextureAtlas(Gdx.files.internal("icons/textfield/textfield.atlas"));
        textFieldSkin.addRegions(textFieldAtlas);

        stage.addActor(connectTable);
        stage.addActor(chatTable);

        initTextButtonStyle();
        initTextFieldStyle();

        // For the Connect
        initPlayButton();
        initEnterButton();
        initUsernameTextField();
        initIpTextField();
        connectTable.setWidth(256f);
        connectTable.add(ipTextField).padBottom(10f).size(256f, 30f).row();
        connectTable.add(usernameTextField).padBottom(10f).size(256f, 30f).row();
        connectTable.add(enterButton).size(120, 30f).padBottom(30f).row();
        connectTable.add(playButton).size(120, 30f).padBottom(30f);
        connectTable.setPosition(10f, 400f);

        // For the chat
        initChatBarTextField();

        //initChatBoxTextArea(" ");

        chatTable.setWidth(512f);
        chatTable.add(chatBoxTextArea).padBottom(10f).size(512f, 256f).row();
        chatTable.add(chatBarTextField).size(512f, 30f).row();
        chatTable.setPosition(400f, 400f);

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                // return super.keyDown(event, keycode);
                if(stage.getKeyboardFocus() == chatBarTextField && event.getKeyCode() == Input.Keys.ENTER) {
                    String content = chatBarTextField.getText();
                    chatBarTextField.setText("");

                    System.out.println("From you: " + content);

                    try {
                        out = new DataOutputStream(conn.getOutputStream());
                        out.writeUTF(content);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }

<<<<<<< HEAD
                    try{
=======
                    try {
>>>>>>> aefb918cfd394b7f20dc1ca02b982d28d65556fc
                        String message = in.readUTF(); //gets the message from server
                        initChatBoxTextArea(message);
                        System.out.println("From someone: " + message);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }

                }
                return true;
            }
        });

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


    private void initChatBarTextField() {

        chatBarTextField = new TextField("Say Something I'm giving up on you", textFieldStyle);

        String message = chatBarTextField.getText();
        initChatBoxTextArea(message);

        chatBarTextField.setAlignment(Align.left);
    }


    private void initChatBoxTextArea(String message) {

        Drawable cursor = textFieldSkin.newDrawable("textfield", Color.WHITE);
        cursor.setMinWidth(2f);
        TextFieldStyle f = new TextFieldStyle(font, Color.WHITE, cursor,
            textFieldSkin.newDrawable("textfield", Color.TEAL), textFieldSkin.getDrawable("textfield"));

        f.fontColor = Color.BLACK;

        chatBoxTextArea = new TextArea(message, f);
        System.out.println("From Chat: " + message);
        chatBoxTextArea.setPrefRows(10f);
        chatBoxTextArea.setDisabled(true);
        chatBoxTextArea.setAlignment(Align.center);
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

                // conn = Gdx.net.newClientSocket(Protocol.TCP, ipTextField.getText(), 8000, null);
                try {
<<<<<<< HEAD
                    conn = new Socket(ipAddress, 8000);
                } catch (Exception e) {
=======
                    conn = new Socket(ipTextField.getText(), 8000);
                }
                catch(Exception e) {
>>>>>>> aefb918cfd394b7f20dc1ca02b982d28d65556fc
                }

                ipTextField.setDisabled(true);
                usernameTextField.setDisabled(true);
                enterButton.setDisabled(true);
                System.out.println("Hello " + username + "! You are trying to connect to: " + ipAddress);
            }
        });

    }

}
