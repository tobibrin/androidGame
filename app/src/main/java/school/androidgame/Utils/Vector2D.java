package school.androidgame.Utils;

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

    public PointF getDirection(PointF destination) {

        float xDifference = destination.x - this.x;
        float yDifference = destination.y - this.y;

        PointF direction = new PointF(xDifference, yDifference);
        float len = this.getLen(xDifference, yDifference);

        direction.x /= len;
        direction.y /= len;
        return direction;
    }
}
