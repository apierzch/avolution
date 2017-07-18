package avolution.game;

import avolution.game.world.Creature;
import avolution.game.world.TileMap;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

public class Avolution extends ApplicationAdapter {
    private PolygonSpriteBatch batch;
    private TileMap map;
    private FPSLabel fps;
    private Creature creature;
    private TickCalculator tickCalculator;

    @Override
    public void create() {
        batch = new PolygonSpriteBatch();
        fps = new FPSLabel();
        map = new TileMap();
        creature = new Creature(map.randomLocation());
        tickCalculator = new TickCalculator();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fps.update();

        long ticks = tickCalculator.countTicks();

        for (int i = 0; i < ticks; i++) {
            tickCalculator.update();
            creature.update();
        }

        batch.begin();
        fps.render(batch);
        map.render(batch);
        creature.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        creature.dispose();
        map.dispose();
        fps.dispose();
    }
}
