package avolution.game.world;

import avolution.game.RandomHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.Arrays.asList;

public class DiamondSquareGenerator<T> {
    private BiConsumer<T, Integer> setter;
    private Function<T, Integer> getter;

    public DiamondSquareGenerator(BiConsumer<T, Integer> setter, Function<T, Integer> getter) {
        this.setter = setter;
        this.getter = getter;
    }

    public void generate(T[][] values, int bound, Random random) {
        SingleRunGenerator generator = new SingleRunGenerator(values, bound, random);
        generator.initCorners();

        int size = values.length;

        while (size > 2) {
            int length = size / 2;

            generator.reduceRandomValueModifier();
            generator.diamondStep(length);
            generator.squareStep(length);

            size = size / 2 + 1;
        }
    }

    void diamondStep(int length, T[][] values, int bound, Random random) {
        SingleRunGenerator generator = new SingleRunGenerator(values, bound, random);
        generator.diamondStep(length);
    }

    void squareStep(int length, T[][] values, int bound, Random random) {
        SingleRunGenerator generator = new SingleRunGenerator(values, bound, random);
        generator.squareStep(length);
    }

    private class SingleRunGenerator {
        private T[][] values;
        private int bound;
        private Random random;
        private float randomValueModifier = 1.0f;

        public SingleRunGenerator(T[][] values, int bound, Random random) {
            this.values = values;
            this.bound = bound;
            this.random = random;
        }

        private void initCorners() {
            int max = values.length - 1;
            setValue(0, 0, random.nextInt(bound));
            setValue(0, max, random.nextInt(bound));
            setValue(max, 0, random.nextInt(bound));
            setValue(max, max, random.nextInt(bound));
        }

        void diamondStep(int length) {
            for (int x = length; x < values.length; x += length * 2) {
                for (int y = length; y < values.length; y += length * 2) {
                    diamondStepFor(x, y, length);
                }
            }
        }

        void squareStep(int length) {
            int yOffset = 0;
            for (int x = 0; x < values.length; x += length) {
                yOffset = yOffset == 0 ? length : 0;
                for (int y = yOffset; y < values.length; y += length * 2) {
                    squareStepFor(x, y, length);
                }
            }
        }

        void reduceRandomValueModifier() {
            randomValueModifier *= 0.9;
        }

        private void diamondStepFor(int x, int y, int l) {
            setValueFor(x, y, asList(getValue(x - l, y - l), getValue(x - l, y + l), getValue(x + l, y - l), getValue(x + l, y + l)));
        }

        private void squareStepFor(int x, int y, int l) {
            List<Integer> factors = new LinkedList<>();
            if (x > 0) {
                factors.add(getValue(x - l, y));
            }
            if (y > 0) {
                factors.add(getValue(x, y - l));
            }
            if (x < values.length - 1) {
                factors.add(getValue(x + l, y));
            }
            if (y < values.length - 1) {
                factors.add(getValue(x, y + l));
            }
            setValueFor(x, y, factors);
        }

        private Integer getValue(int x, int y) {
            return getter.apply(values[x][y]);
        }

        private void setValue(int x, int y, int value) {
            setter.accept(values[x][y], value);
        }

        private void setValueFor(int x, int y, List<Integer> factors) {
            int average = (int) factors.stream().mapToInt(Integer::intValue).average().orElseThrow(IllegalArgumentException::new);
            setValue(x, y, (int) (average + (randomValue() * randomValueModifier)));
        }

        private int randomValue() {
            return RandomHelper.withRandom(random).nextIntBetweenIncl(-(bound / 2), bound / 2);
        }
    }
}
