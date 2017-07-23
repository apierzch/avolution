package avolution.game.system;

import avolution.game.world.TileMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;

public class Camera {
    private OrthographicCamera gdxCamera;

    public Camera(TileMap map) {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        gdxCamera = new OrthographicCamera(30, 30 * (h / w));
        gdxCamera.position.set(map.size() / 2, map.size() / 2, 0);
        gdxCamera.zoom = 100;
    }

    public void setProjection(Batch batch) {
        batch.setProjectionMatrix(gdxCamera.combined);
    }

    public void update() {
        gdxCamera.update();
    }

    public float zoom() {
        return gdxCamera.zoom;
    }

    public void zoomIn() {
        gdxCamera.zoom -= gdxCamera.zoom * 0.1f;
    }

    public void zoomOut() {
        gdxCamera.zoom += gdxCamera.zoom * 0.1f;
    }

    public void unproject(Vector3 coords) {
        gdxCamera.unproject(coords);
    }

    public Vector3 position() {
        return gdxCamera.position;
    }

    public void translate(Vector3 target) {
        gdxCamera.translate(target);
    }
}
