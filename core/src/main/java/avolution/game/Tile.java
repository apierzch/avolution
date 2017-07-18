package avolution.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {

    public static final int SIZE = 100;
    private Texture texture;
    private int x;
    private int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        texture = randomColorTexture();
    }

    private Texture randomColorTexture() {
        Pixmap pixelmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixelmap.setColor(StaticGlobals.RANDOM.nextFloat(), StaticGlobals.RANDOM.nextFloat(), StaticGlobals.RANDOM.nextFloat(), 1);
        pixelmap.fill();
        return new Texture(pixelmap);
    }

    void draw(PolygonSpriteBatch batch) {
        int x = this.x * SIZE;
        int y = this.y * SIZE;
        PolygonRegion region = new PolygonRegion(new TextureRegion(texture),
                new float[]{
                        x, y,
                        x + SIZE, y,
                        x + SIZE, y + SIZE,
                        x, y + SIZE
                }, new short[]{
                0, 1, 2,
                0, 2, 3
        });

        PolygonSprite sprite = new PolygonSprite(region);
        sprite.draw(batch);
    }
}
