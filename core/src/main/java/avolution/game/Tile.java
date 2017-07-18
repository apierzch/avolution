package avolution.game;

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
        texture = TextureHelper.randomColorTexture();
    }

    void render(PolygonSpriteBatch batch) {
        int x = this.x * tileSize;
        int y = this.y * tileSize;
        PolygonRegion region = new PolygonRegion(new TextureRegion(texture),
                new float[]{
                        x, y,
                        x + tileSize, y,
                        x + tileSize, y + tileSize,
                        x, y + tileSize
                }, new short[]{
                0, 1, 2,
                0, 2, 3
        });

        PolygonSprite sprite = new PolygonSprite(region);
        sprite.draw(batch);
    }
}
