package avolution.game;

import avolution.game.world.TileMap;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Avolution extends ApplicationAdapter {
    private PolygonSpriteBatch batch;
    private SpriteBatch uiBatch;
    private TileMap map;
    private FPSLabel fps;
    private TickCalculator tickCalculator;
    private boolean paused;
    private OrthographicCamera camera;

    @Override
    public void create() {
        map = new TileMap();
        initCamera();
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
        batch.setProjectionMatrix(camera.combined);

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
    }

    private void initCamera() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(30, 30 * (h / w));
        camera.position.set(map.size() / 2, map.size() / 2, 0);
        camera.zoom = 50;
    }

    @Override
    public void dispose() {
        batch.dispose();
        uiBatch.dispose();
        map.dispose();
        fps.dispose();
    }
}
