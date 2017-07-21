package avolution.game;

import java.util.Random;

public class RandomHelper {
    public static final Random RANDOM = new Random(42);
    public static final RandomHelper defaultRandomHelper = new RandomHelper(RANDOM);
    private final Random random;

    private RandomHelper(Random random) {
        this.random = random;
    }

    public int nextIntBetweenIncl(int min, int max) {
        return random.nextInt(max + 1 - min) + min;
    }

    public static RandomHelper withRandom(Random random) {
        return new RandomHelper(random);
    }
}
