package school.androidgame.utils;

import android.graphics.PointF;

/**
 * Created by Tobi on 29.10.2017.
 */

public class Vector2D extends PointF {

    public Vector2D(float x, float y) {
        super(x,y);
    }

    public float getLen(float x, float y) {
        return (float)Math.sqrt(x*x + y*y);
    }

    public Vector2D getDirection(PointF destination) {
        return this.calcDirection(destination.x, destination.y);
    }

    public Vector2D getDirection(Vector2D destination) {
        return this.calcDirection(destination.x, destination.y);
    }

    public Vector2D getDirection(float x, float y) {
        return this.calcDirection(x, y);
    }

    private Vector2D calcDirection(float x, float y) {
        float xDifference = x - this.x;
        float yDifference = y - this.y;

        Vector2D direction = new Vector2D(xDifference, yDifference);
        float len = this.getLen(xDifference, yDifference);

        direction.x /= len;
        direction.y /= len;

        return direction;
    }
}
