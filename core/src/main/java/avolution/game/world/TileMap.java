package avolution.game.world;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import static avolution.game.RandomHelper.RANDOM;

public class TileMap implements Disposable {
    private static final int MAP_SIZE = 3;
    private static final int TILE_SIZE = 100;
    public static final int INITIAL_CREATURES = 10;
    private final List<Creature> creatures = new LinkedList<>();
    private Tile[][] tiles = new Tile[MAP_SIZE][MAP_SIZE];

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
        eachTile(tile -> tile.render(batch));
        creatures.forEach(creature -> creature.render(batch));
    }

    private void generateCreatures() {
        for (int i = 0; i < INITIAL_CREATURES; i++) {
            creatures.add(new Creature(randomLocation()));
        }
    }

    private void generateTiles() {
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                tiles[x][y] = new Tile(x, y, TILE_SIZE);
            }
        }

        initFoods();
    }

    private void initFoods() {
        DiamondSquareGenerator<Tile> generator = new DiamondSquareGenerator<>(Tile::updateFood, Tile::food);
        generator.generate(tiles, 100, RANDOM);
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
        eachTile(Tile::dispose);

        for (Creature creature : creatures) {
            creature.dispose();
        }
    }

    private void eachTile(Consumer<Tile> fun) {
        for (Tile[] rowTiles : tiles) {
            for (Tile tile : rowTiles) {
                fun.accept(tile);
            }
        }
    }
}
