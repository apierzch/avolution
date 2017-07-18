package avolution.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

public class Creature {

    public static final int BASE_RADIUS = 50;
    private final int radius;
    private Location location;
    private Texture texture;

    public Creature(Location location) {
        this.location = location;
        radius = BASE_RADIUS;
        generateTexture();
    }

    public void render(PolygonSpriteBatch batch) {
        batch.draw(texture, location.x(), location.y());
    }

    private void generateTexture() {
        Pixmap pixelmap = new Pixmap(radius * 2, radius * 2, Pixmap.Format.RGBA8888);
        pixelmap.setColor(StaticGlobals.RANDOM.nextFloat(), StaticGlobals.RANDOM.nextFloat(), StaticGlobals.RANDOM.nextFloat(), 1);
        pixelmap.fillCircle(radius, radius, radius);
        texture = new Texture(pixelmap);
    }
}
