package avolution.game;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

import java.util.LinkedList;
import java.util.List;

public class TileMap {
    private static final int MAP_SIZE = 10;
    private List<Tile> tiles = new LinkedList<>();

    public TileMap() {
        generateTiles();
    }

    public void render(PolygonSpriteBatch batch) {
        for (Tile tile : tiles) {
            tile.render(batch);
        }
    }

    private void generateTiles() {
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                tiles.add(new Tile(x, y));
            }
        }
    }
}
