package com.panzerfauster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.panzerfauster.Panzerfauster;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Panzerfauster";
//		config.fullscreen = true;
		config.width = 1000;
		config.height = 600;
		new LwjglApplication(new Panzerfauster(), config);
	}
}
