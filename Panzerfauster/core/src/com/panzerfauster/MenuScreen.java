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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

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
    private DataOutputStream chatOut;
    private DataInputStream  chatIn;
    private boolean isInitiated = false;
    private String username;

    private DatagramSocket socket;
    private ArrayList<String> chatString = new ArrayList<String>();

    private TextButton mechanicsButton;


    private MenuScreen() {

    }


    public static MenuScreen getScreen() {
        return screen;
    }


    @Override
    public void show() {
        if(!isInitiated) {
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
            initMechanicsButton();
            connectTable.setWidth(256f);
            connectTable.add(mechanicsButton).size(120, 30f).padBottom(30f).row();
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
                    // If you hit enter, send something to the server
                    if(stage.getKeyboardFocus() == chatBarTextField && event.getKeyCode() == Input.Keys.ENTER) {
                        String content = chatBarTextField.getText();
                        chatBarTextField.setText("");

                        try {
                            chatOut.writeUTF(content);
                            chatString.add(content);

                            if(chatString.size() == 10){
                                adjustChatString();
                            }
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }

                    }
                    return true;
                }
            });

        }

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

        chatBarTextField = new TextField("", textFieldStyle);

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
        usernameTextField = new TextField("", textFieldStyle);
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

                //insert UDP here
                try{
                    send("?connect " + usernameTextField.getText() + " " + ipTextField.getText() );
                    System.out.println();
                    chatBoxTextArea.appendText("Game Loading... Waiting for connections\n");

                }catch(Exception e){}

                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                try{
                    socket.receive(packet);
                }catch(Exception ioe){/*lazy exception handling :)*/}

                String serverData=new String(buf);
                serverData=serverData.trim();

                if(serverData.startsWith("?start")) {
                    //begin game
                    Panzerfauster.getInstance().setGameScreen();
                    try{
                        GameState newGame = new GameState();
                                newGame.setAddress(InetAddress.getByName(ipTextField.getText()));
                    }catch(Exception e){}

                }

            }
        });

    }

    private void initMechanicsButton() {

        mechanicsButton = new TextButton("Mechanics", textButtonStyle);

        mechanicsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent ev, float x, float y) {
                Panzerfauster.getInstance().setMechanicsScreen();
            }
        });

    }


    private void initEnterButton() {

        enterButton = new TextButton("Enter", textButtonStyle);

        enterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent ev, float x, float y) {
               final String ipAddress = ipTextField.getText();
                username = usernameTextField.getText();


                if(enterButton.isDisabled()) {
                    return;
                }

                try {
                    conn = new Socket(ipAddress, 8000);

                    chatIn = new DataInputStream(conn.getInputStream());
                    chatOut = new DataOutputStream(conn.getOutputStream());

                    ipTextField.setDisabled(true);
                    usernameTextField.setDisabled(true);
                    enterButton.setDisabled(true);
                }
                catch(Exception e) {
                    System.out.println("Failed to create a socket");
                }

                System.out.println("Hello " + username + "! You are trying to connect to: " + ipAddress + "\n");

                // Anonymous thread listener
                new Thread(new Runnable() {
                    public void run() {

                        chatBoxTextArea.appendText("\nTrying to connect\n");

                        String s;

                        //send username
                        try {
                            chatOut.writeUTF(username);
                        }
                        catch(IOException e) {
                            return;
                        }
                        catch(Exception e) {
                            return;
                        }

                        //Listen for success
                        try {
                            s = chatIn.readUTF();
                        }
                        catch(Exception e) {
                            return;
                        }
                        //if !success, enable everything and append "pick a different username"
                        if(!s.equals("?success")) {
                            System.out.println("Sent: " + s);
                            chatBoxTextArea.appendText("\nUsername already is taken. Try again\n");
                            ipTextField.setDisabled(false);
                            usernameTextField.setDisabled(false);
                            enterButton.setDisabled(false);

                            try{
                                conn.close();
                            } catch(Exception e) {}

                            return;
                        }

                        // Success

                        chatBoxTextArea.appendText("\nConnected to " + ipAddress + "\n");

                        //Main chat loop
                        while (true) {
                            try {
                                s = chatIn.readUTF();
                                chatBoxTextArea.appendText(s);
                            }
                            catch(IOException e) {
                                // Disconnected
                                return;
                            }
                            catch(Exception e) {
                                continue;
                            }

                        }
                    }
                }) {
                }.start();
            }
        });

    }

    private void adjustChatString(){
        chatString.remove(0);

    }

    public void send(String msg){
        try{
            byte[] buf = msg.getBytes();

            //InetAddress address = InetAddress.getByName(server);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(ipTextField.getText()),4444);
            socket.send(packet);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
