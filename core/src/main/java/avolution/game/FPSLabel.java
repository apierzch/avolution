package avolution.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class FPSLabel {

    private final BitmapFont font;
    private long lastTimeCounted;
    private long sinceChange;
    private int frameRate;

    public FPSLabel() {
        font = new BitmapFont();
    }

    public void update() {
        long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();

        sinceChange += delta;
        if (sinceChange >= 1000) {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }

    }

    public void render(Batch batch) {
        font.draw(batch, frameRate + " fps", 3, Gdx.graphics.getHeight() - 3);
    }
}
