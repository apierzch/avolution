package avolution.game.world;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.LinkedList;
import java.util.List;

import static avolution.game.RandomHelper.RANDOM;

public class TileMap implements Disposable {
    private static final int MAP_SIZE = 100;
    private static final int TILE_SIZE = 100;
    private final List<Creature> creatures = new LinkedList<>();
    private List<Tile> tiles = new LinkedList<>();

    public TileMap() {
        generateTiles();
        generateCreatures();
    }

    public void update() {
        for (Creature creature : creatures) {
            creature.update();
        }
    }

    public void render(PolygonSpriteBatch batch) {
        for (Tile tile : tiles) {
            tile.render(batch);
        }

        for (Creature creature : creatures) {
            creature.render(batch);
        }
    }

    private void generateCreatures() {
        for (int i = 0; i < 30; i++) {
            creatures.add(new Creature(randomLocation()));
        }
    }

    private void generateTiles() {
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                tiles.add(new Tile(x, y, TILE_SIZE));
            }
        }
    }

    private Location randomLocation() {
        int realSize = realSize();
        int x = RANDOM.nextInt(realSize);
        int y = RANDOM.nextInt(realSize);
        return new Location(x, y);
    }

    public float size() {
        return realSize();
    }

    private int realSize() {
        return MAP_SIZE * TILE_SIZE;
    }

    @Override
    public void dispose() {
        for (Tile tile : tiles) {
            tile.dispose();
        }

        for (Creature creature : creatures) {
            creature.dispose();
        }
    }
}
