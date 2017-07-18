package avolution.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;

public class Avolution extends ApplicationAdapter {
	private PolygonSpriteBatch batch;
	private TileMap map;
	private FPSLabel fps;

	@Override
	public void create () {
		batch = new PolygonSpriteBatch();
		fps = new FPSLabel();
		map = new TileMap();
	}



	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		fps.update();

		batch.begin();
		fps.render(batch);
		map.render(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
