package avolution.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.LinkedList;
import java.util.List;

public class Avolution extends ApplicationAdapter {
	private static final int MAP_SIZE = 10;
	private PolygonSpriteBatch batch;
	private List<Tile> tiles;
	private BitmapFont font;
	private long lastTimeCounted;
	private long sinceChange;
	private int frameRate;

	@Override
	public void create () {
		batch = new PolygonSpriteBatch();
		tiles = new LinkedList<>();
		font = new BitmapFont();

		generateTiles();
	}



	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		updateFPS();

		batch.begin();
		for (Tile tile : tiles) {
			tile.draw(batch);
		}
		batch.end();
	}

	private void updateFPS() {
		long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
		lastTimeCounted = TimeUtils.millis();

		sinceChange += delta;
		if(sinceChange >= 1000) {
			sinceChange = 0;
			frameRate = Gdx.graphics.getFramesPerSecond();
		}
		batch.begin();
		font.draw(batch, frameRate + " fps", 3, Gdx.graphics.getHeight() - 3);
		batch.end();
	}

	private void generateTiles() {
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				tiles.add(new Tile(x, y));
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
