package school.androidgame.utils;

import android.graphics.PointF;

/**
 * Created by Tobi on 29.10.2017.
 */

public class Vector2D extends PointF {

    public Vector2D(float x, float y) {
        super(x,y);
    }

    public PointF getDirection(PointF dest) {
        float xDifference = dest.x - this.x,
        yDifference = dest.y - this.y,
        len = length(xDifference, yDifference);

        return new Vector2D(xDifference/len, yDifference/len);
    }
}
