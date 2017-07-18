package avolution.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {

    private Texture texture;
    private int x;
    private int y;
    private int tileSize;

    public Tile(int x, int y, int tileSize) {
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        texture = generateTexture();
    }

    void render(PolygonSpriteBatch batch) {
        PolygonRegion polygon = makePolygon();
        PolygonSprite sprite = new PolygonSprite(polygon);

        sprite.draw(batch);
    }

    private PolygonRegion makePolygon() {
        int x = this.x * tileSize;
        int y = this.y * tileSize;
        return new PolygonRegion(new TextureRegion(texture),
                new float[]{
                        x, y,
                        x + tileSize, y,
                        x + tileSize, y + tileSize,
                        x, y + tileSize
                }, new short[]{
                0, 1, 2,
                0, 2, 3
        });
    }

    private Texture generateTexture() {
        Pixmap pixelmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixelmap.setColor(StaticGlobals.RANDOM.nextFloat(), StaticGlobals.RANDOM.nextFloat(), StaticGlobals.RANDOM.nextFloat(), 1);
        pixelmap.fill();
        return new Texture(pixelmap);
    }
}
