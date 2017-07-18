package avolution.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

public class Creature {

    private Location location;
    private Texture texture;

    public Creature(Location location) {
        this.location = location;
        texture = TextureHelper.randomColorCircleTexture();
    }

    public void render(PolygonSpriteBatch batch) {
        batch.draw(texture, location.x(), location.y());
    }
}
