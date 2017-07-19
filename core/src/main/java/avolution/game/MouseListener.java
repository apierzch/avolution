package avolution.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class MouseListener extends AbstractMouseListener implements InputProcessor {
    public static final float MAGIC_NUMBER = 0.0156f;
    private OrthographicCamera camera;
    private Vector3 lastTouch = new Vector3();


    public MouseListener(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastTouch.set(screenX, screenY, 0);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 target = new Vector3();
        target.set(screenX, screenY, 0).sub(lastTouch).scl(-1, 1, 0).scl(MAGIC_NUMBER * camera.zoom);
        camera.translate(target);
        lastTouch.set(screenX, screenY, 0);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        camera.zoom += (camera.zoom * 0.1 * amount);
        System.out.println("zoom: " + camera.zoom);
        return true;
    }
}
