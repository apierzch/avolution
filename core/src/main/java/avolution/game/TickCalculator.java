package avolution.game;

import com.badlogic.gdx.utils.TimeUtils;

public class TickCalculator {

    private long lastTick = TimeUtils.nanoTime();
    public static final int SECOND = 1_000_000_000;
    private static long TICK_LENGTH = SECOND / 60;

    public long countTicks() {
        long elapsed = TimeUtils.timeSinceNanos(lastTick);
        return elapsed / TICK_LENGTH;
    }


    public void update() {
        lastTick = TimeUtils.nanoTime();
    }
}
