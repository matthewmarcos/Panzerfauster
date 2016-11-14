package com.panzerfauster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.panzerfauster.Panzerfauster;

import java.awt.*;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Panzerfauster";
//		config.fullscreen = true;
        config.width = 1000;
        config.height = 600;

//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        double width2 = screenSize.getWidth();
//        double height2 = screenSize.getHeight();

//        config.width = (int)width2;
//        config.height = (int)height2;
        new LwjglApplication(new Panzerfauster(), config);
    }
}
