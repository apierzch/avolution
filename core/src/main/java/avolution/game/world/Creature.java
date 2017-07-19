package avolution.game.world;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import static avolution.game.StaticGlobals.RANDOM;

public class Creature implements Disposable{

    public static final int BASE_RADIUS = 50;
    private final int radius;
    private Location location;
    private Texture texture;
    private Vector2 direction;

    public Creature(Location location) {
        this.location = location;
        radius = BASE_RADIUS;
        generateTexture();
        direction = new Vector2(RANDOM.nextFloat(), RANDOM.nextFloat()).nor();
    }

    public void update() {
        location = location.move(direction);
    }

    public void render(PolygonSpriteBatch batch) {
        batch.draw(texture, location.x(), location.y());
    }

    private void generateTexture() {
        if (texture != null) {
            texture.dispose();
        }

        Pixmap pixelmap = new Pixmap(radius * 2, radius * 2, Pixmap.Format.RGBA8888);
        pixelmap.setColor(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat(), 1);
        pixelmap.fillCircle(radius, radius, radius-1);
        texture = new Texture(pixelmap);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
