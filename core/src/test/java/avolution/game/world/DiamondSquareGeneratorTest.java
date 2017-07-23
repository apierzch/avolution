package avolution.game.world;

import org.junit.Test;

import java.util.Random;

import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiamondSquareGeneratorTest {

    private final Random random = mock(Random.class);
    private DiamondSquareGenerator<Value> generator;

    @Test
    public void inits_corners() {
        Value[][] values = new Value[][]{
                {val(0), val(0)},
                {val(0), val(0)},
        };
        generator = new DiamondSquareGenerator<>(Value::set, Value::get);
        when(random.nextInt(10))
                .thenReturn(1)
                .thenReturn(5)
                .thenReturn(6)
                .thenReturn(4);

        generator.generate(values, 10, random);

        assertThat(values).isEqualTo(new Value[][]{
                {val(1), val(5)},
                {val(6), val(4)},
        });
    }

    @Test
    public void first_diamond_step() {
        Value[][] values = new Value[][]{
                {val(3), val(0), val(3)},
                {val(0), val(0), val(0)},
                {val(3), val(0), val(3)},
        };
        generator = new DiamondSquareGenerator<>(Value::set, Value::get);
        when(random.nextInt(11))
                .thenReturn(5);

        generator.diamondStep(1, values, 10, random);

        assertThat(values).isEqualTo(new Value[][]{
                {val(3), val(0), val(3)},
                {val(0), val(3), val(0)},
                {val(3), val(0), val(3)},
        });
    }

    @Test
    public void first_diamond_step_with_avg() {
        Value[][] values = new Value[][]{
                {val(4), val(0), val(6)},
                {val(0), val(0), val(0)},
                {val(4), val(0), val(6)}
        };
        generator = new DiamondSquareGenerator<>(Value::set, Value::get);
        when(random.nextInt(11))
                .thenReturn(5);

        generator.diamondStep(1, values, 10, random);

        assertThat(values).isEqualTo(new Value[][]{
                {val(4), val(0), val(6)},
                {val(0), val(5), val(0)},
                {val(4), val(0), val(6)}
        });
    }

    @Test
    public void first_diamond_step_with_random() {
        Value[][] values = new Value[][]{
                {val(4), val(0), val(6)},
                {val(0), val(0), val(0)},
                {val(4), val(0), val(6)}
        };
        generator = new DiamondSquareGenerator<>(Value::set, Value::get);
        when(random.nextInt(11))
                .thenReturn(4);

        generator.diamondStep(1, values, 10, random);

        assertThat(values).isEqualTo(new Value[][]{
                {val(4), val(0), val(6)},
                {val(0), val(4), val(0)},
                {val(4), val(0), val(6)}
        });
    }

    @Test
    public void executes_both_steps_in_generation() {
        Value[][] values = new Value[][]{
                {val(0), val(0), val(0)},
                {val(0), val(0), val(0)},
                {val(0), val(0), val(0)}
        };
        generator = new DiamondSquareGenerator<>(Value::set, Value::get);
        when(random.nextInt(10))
                .thenReturn(3)
                .thenReturn(3)
                .thenReturn(9)
                .thenReturn(3);
        when(random.nextInt(11))
                .thenReturn(7)
                .thenReturn(5)
                .thenReturn(5)
                .thenReturn(5)
                .thenReturn(5);

        generator.generate(values, 10, random);

        assertThat(values).isEqualTo(new Value[][]{
                {val(3), val(4), val(3)},
                {val(6), val(6), val(4)},
                {val(9), val(6), val(3)}
        });
    }

    @Test
    public void square_step() {
        Value[][] values = new Value[][]{
                {val(3), val(0), val(3)},
                {val(0), val(6), val(0)},
                {val(9), val(0), val(3)}
        };
        generator = new DiamondSquareGenerator<>(Value::set, Value::get);
        when(random.nextInt(11)).thenReturn(5);

        generator.squareStep(1, values, 10, random);

        assertThat(values).isEqualTo(new Value[][]{
                {val(3), val(4), val(3)},
                {val(6), val(6), val(4)},
                {val(9), val(6), val(3)}
        });
    }

    @Test
    public void many_steps() {
        Value[][] values = arrayOf(5, 0);
        generator = new DiamondSquareGenerator<>(Value::set, Value::get);
        when(random.nextInt(10)).thenReturn(5);
        when(random.nextInt(11)).thenReturn(5);

        generator.generate(values, 10, random);

        assertThat(values).isEqualTo(arrayOf(5, 5));
    }

    private Value[][] arrayOf(int size, int initialValue) {
        Value[][] values = new Value[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                values[x][y] = val(initialValue);
            }
        }
        return values;
    }

    private Value val(int value) {
        return new Value(value);
    }

    private static class Value {
        private int value;

        public Value(int value) {
            this.value = value;
        }

        public void set(Integer value) {
            this.value = value;
        }

        public Integer get() {
            return value;
        }

        @Override
        public String toString() {
            return valueOf(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Value value1 = (Value) o;

            return value == value1.value;
        }

        @Override
        public int hashCode() {
            return value;
        }
    }
}