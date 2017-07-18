package avolution.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class TextureHelper {
    public static Texture randomColorTexture() {
        Pixmap pixelmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixelmap.setColor(StaticGlobals.RANDOM.nextFloat(), StaticGlobals.RANDOM.nextFloat(), StaticGlobals.RANDOM.nextFloat(), 1);
        pixelmap.fill();
        return new Texture(pixelmap);
    }

    public static Texture randomColorCircleTexture() {
        Pixmap pixelmap = new Pixmap(110, 110, Pixmap.Format.RGBA8888);
        pixelmap.setColor(StaticGlobals.RANDOM.nextFloat(), StaticGlobals.RANDOM.nextFloat(), StaticGlobals.RANDOM.nextFloat(), 1);
        pixelmap.fillCircle(50,50, 50);
        return new Texture(pixelmap);
    }
}
