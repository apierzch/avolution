package avolution.game;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Location move() {
        return new Location(lightlyOff(x), lightlyOff(y));
    }

    private int lightlyOff(int value) {
        return value + plusMinus(1);
    }

    private int plusMinus(int i) {
        return StaticGlobals.RANDOM.nextInt(i * 2 + 1) - i;
    }
}
