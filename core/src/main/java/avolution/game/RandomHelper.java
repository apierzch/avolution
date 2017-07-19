package avolution.game;

import java.util.Random;

public class RandomHelper {
    public static Random RANDOM = new Random(42);

    public static int nextIntBetweenIncl(int min, int max) {
        return RANDOM.nextInt(max + 1 - min) + min;
    }
}
