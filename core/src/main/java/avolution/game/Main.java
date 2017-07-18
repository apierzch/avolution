package avolution.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import static com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration.getDesktopDisplayMode;

public class Main {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.fullscreen = true;

        config.height = getDesktopDisplayMode().height;
        config.width = getDesktopDisplayMode().width;
        new LwjglApplication(new Avolution(), config);
    }
}
