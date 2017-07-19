package avolution.game.world;

import com.badlogic.gdx.math.Vector2;

public class Location {
    private Vector2 location = new Vector2();

    public Location(float x, float y) {
        location.set(x, y);
    }

    public Location(Vector2 location) {
        this.location = location;
    }

    public float x() {
        return location.x;
    }

    public float y() {
        return location.y;
    }

    public Location move(Vector2 direction) {
        return new Location(location.cpy().add(direction));
    }
}
