package avolution.game.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import static avolution.game.ColorUtils.HSV_to_RGB;
import static avolution.game.RandomHelper.RANDOM;

public class Tile implements Disposable {

    private Texture texture;
    private int x;
    private int y;
    private int tileSize;
    private float food;

    public Tile(int x, int y, int tileSize) {
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        this.food = RANDOM.nextFloat() * 100;
        generateTexture();
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

    private void generateTexture() {
        if (texture != null) {
            texture.dispose();
        }
        Pixmap pixelmap = new Pixmap(1, 1, Pixmap.Format.RGB888);

        pixelmap.setColor(colorForFood());
        pixelmap.fill();
        texture = new Texture(pixelmap);
    }

    private Color colorForFood() {
        float points = (food * 175) / 100;
        int h = 120;
        float s = Math.min(points, 100);
        points -= s;
        float v = 100 - points;
        return HSV_to_RGB(h, s, v);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
