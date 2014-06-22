package se.emillindau.spacecowboy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.emillindau.spacecowboy.SpaceCowboy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "SpaceCowboy";
        config.width = 1280;
        config.height = 720;
        config.vSyncEnabled = true;
        config.resizable = false;

		new LwjglApplication(new SpaceCowboy(), config);
	}
}
