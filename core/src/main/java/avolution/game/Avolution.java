package avolution.game;

import avolution.game.system.Camera;
import avolution.game.world.TileMap;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Avolution extends ApplicationAdapter {
    private PolygonSpriteBatch batch;
    private SpriteBatch uiBatch;
    private TileMap map;
    private FPSLabel fps;
    private TickCalculator tickCalculator;
    private boolean paused;
    private Camera camera;

    @Override
    public void create() {
        map = new TileMap();
        camera = new Camera(map);
        batch = new PolygonSpriteBatch();
        uiBatch = new SpriteBatch();
        fps = new FPSLabel();
        tickCalculator = new TickCalculator();
        Gdx.input.setInputProcessor(new MouseListener(camera));
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        camera.update();
        camera.setProjection(batch);

        fps.update();

        long ticks = tickCalculator.countTicks();

        for (int i = 0; i < ticks; i++) {
            tickCalculator.update();
            if (!paused) {
                map.update();
            }
        }

        batch.begin();
        map.render(batch);
        batch.end();

        uiBatch.begin();
        fps.render(uiBatch);
        uiBatch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            paused = !paused;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.PLUS)) {
            camera.zoomIn();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.MINUS)) {
            camera.zoomOut();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        uiBatch.dispose();
        map.dispose();
        fps.dispose();
    }
}
