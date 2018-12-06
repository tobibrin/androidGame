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

    public Vector2D getDirection(PointF destination,float puffer) {
        return this.calcDirection(destination.x, destination.y, puffer);
    }

    public Vector2D getDirection(Vector2D destination, float puffer) {
        return this.calcDirection(destination.x, destination.y, puffer);
    }

    private Vector2D calcDirection(float x, float y, float puffer) {
        float xDifference = x - this.x;
        float yDifference = y - this.y;
        Vector2D direction = new Vector2D(0,0);

        if (Math.abs(xDifference) > puffer && Math.abs(yDifference) > puffer) {
            float len = this.getLen(xDifference, yDifference);
            direction.x = xDifference / len;
            direction.y = yDifference / len;
        }
        return direction;
    }
}
